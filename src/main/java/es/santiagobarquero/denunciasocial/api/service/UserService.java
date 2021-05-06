package es.santiagobarquero.denunciasocial.api.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.UserRepository;
import es.santiagobarquero.denunciasocial.auxiliary.ArtroponetConstants;
import es.santiagobarquero.denunciasocial.auxiliary.LogAction;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.FailLoginException;

@Service
public class UserService implements ServiceInterface<UserDvo, User> {
	
	private Logger logger = null;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private UserRepository userRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TokenService tokenService;
	
	@PostConstruct
	public void init() {
		logger = LogAction.getLogger(UserService.class);
	}
	
	public TokenDvo doLogin(String username, String password) throws FailLoginException {
		logger.info(String.format("%s is trying to login --", username));
		UserDvo userDvo = findUserByUsername(username);
		if(userDvo == null) {
			logger.info(String.format("-- Login KO for %s --", username));
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}
		
		boolean okCredentials = BCrypt.checkpw(password, userDvo.getPassword());
		
		if(okCredentials) {
			TokenDvo oldToken = userDvo.getTokenDvo();
			// Generate a new token for the next auths
			TokenDvo newToken = tokenService.generate();
			logger.info(String.format("-- Login OK for %s --", username));
			logger.info(String.format("-- The old token is -> %s --", userDvo.getTokenDvo().getUuidToken()));
			logger.info(String.format("-- The new token is -> %s --", newToken.getUuidToken()));
			userDvo.setTokenDvo(newToken);
			userDvo = update(userDvo, true);
			logger.info("-- Token updated --");
			// Delete the old token
			tokenService.delete(oldToken);
			logger.info("-- Old token has been removed --");
			return userDvo.getTokenDvo();
		} else {
			logger.info(String.format("-- Login KO for %s --", username));
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}
		
	
	}
	
	/*
	 * PERSIST METHODS
	 */
	@Transactional
	public UserDvo createNewUser(UserDvo userDvo, boolean generateToken) throws ParseException {
		UserDvo thisUserDvo = userDvo;
		boolean thisGenerateToken = generateToken;
		if(thisUserDvo == null || userRepository.getUserByUsername(thisUserDvo.getUsername()) != null) {
			return thisUserDvo;
		}
		thisUserDvo.setActive(thisUserDvo.getActive());
		thisUserDvo.setPassword(Utilities.encryptPassword(thisUserDvo.getPassword()));
		if(thisGenerateToken) {
			thisUserDvo.setTokenDvo(tokenService.generate());
		}
		thisUserDvo.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		return create(thisUserDvo, true, false);
	}
	
	@Transactional
	@Override
	public void delete(UserDvo userDvo, boolean flushOnFinish) {
		userRepository.delete(userDvo.getEntityObject(false));
	}
	
	
	@Override
	public UserDvo update(UserDvo userDvo, boolean flushOnFinish) {
		UserDvo updatedUserDvo = userRepository.save(userDvo.getEntityObject(true)).getObjectView(true);
		if(flushOnFinish) {
			userRepository.flush();
		}
		return updatedUserDvo;
	}
	
	@Transactional
	@Override
	public UserDvo create(UserDvo userDvo, boolean lazy, boolean flushOnFinish) {
		return userRepository.save(userDvo.getEntityObject(lazy)).getObjectView(lazy);
	}
	
	/*
	 * READ/GET INFO METHODS
	 */
	
	@Override
	public List<UserDvo> getAllsDvo(boolean lazy) {
		List<User> allUsersList = userRepository.findAll();
		
		if(Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<>(ArtroponetConstants.ZERO);
		}
		
		List<UserDvo> result = new ArrayList<>(ArtroponetConstants.ZERO);
		for(User u : allUsersList) {
			result.add(u.getObjectView(lazy));
		}
		return result;
	}
	
	@Override
	public List<User> getAllsEntity(boolean lazy) {
		List<User> allUsersList = userRepository.findAll();
		
		if(Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<>(ArtroponetConstants.ZERO);
		}
		
		return allUsersList;
	}
	
	public UserDvo findUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		if(user == null) {
			return null;
		}
		
		return user.getObjectView(true);
	}

	public UserDvo findIt(Long pkUserLong) {
		User userFinded = userRepository.getOne(pkUserLong);
		if(userFinded == null) {
			return null;
		}
		return userFinded.getObjectView(true);
		
	}

}
