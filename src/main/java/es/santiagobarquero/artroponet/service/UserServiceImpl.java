package es.santiagobarquero.artroponet.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.LogAction;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.auxiliary.exceptions.FailLoginException;
import es.santiagobarquero.artroponet.dvo.converters.UserConverter;
import es.santiagobarquero.artroponet.model.entity.User;
import es.santiagobarquero.artroponet.model.repository.UserRepository;
import es.santiagobarquero.artroponet.resources.dvo.TokenDvo;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private Logger logger = LogAction.getLogger(IUserService.class);

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED
	// !!!! //

	@Autowired
	private UserRepository userRepository;

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED
	// !!!! //

	@Autowired
	private ITokenService tokenService;

	public UserServiceImpl(UserRepository userRepository, TokenServiceImpl tokenService) {
		super();
		this.userRepository = userRepository;
		this.tokenService = tokenService;
	}
	
	public UserServiceImpl() {
		super();
	}

	@Override
	public UserDvo doLogin(String username, String password) throws FailLoginException {
		logger.info(String.format("%s is trying to login --", username));
		UserDvo userDvo = findUserByUsername(username);
		if (userDvo == null) {
			logger.info(String.format("-- Login KO for %s --", username));
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}
		
		boolean okCredentials = BCrypt.checkpw(password, userDvo.getPassword());

		if (okCredentials) {
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
			tokenService.delete(oldToken, false);
			logger.info("-- Old token has been removed --");
			return userDvo;
		} else {
			logger.info(String.format("-- Login KO for %s --", username));
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}

	}

	@Override
	public UserDvo findUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			return null;
		}

		return UserConverter.getObjectView(user, false);
	}

	@Override
	public UserDvo findIt(Long pkUserLong) {
		User userFinded = userRepository.getOne(pkUserLong);
		if (userFinded == null) {
			return null;
		}
		return UserConverter.getObjectView(userFinded, false);

	}

	@Transactional
	public UserDvo createNewUser(UserDvo userDvo, boolean generateToken) throws ParseException {
		UserDvo thisUserDvo = userDvo;
		boolean thisGenerateToken = generateToken;
		if (thisUserDvo == null || userRepository.getUserByUsername(thisUserDvo.getUsername()) != null) {
			return thisUserDvo;
		}
		thisUserDvo.setActive(thisUserDvo.getActive());
		thisUserDvo.setPassword(Utilities.encryptPassword(thisUserDvo.getPassword()));
		if (thisGenerateToken) {
			thisUserDvo.setTokenDvo(tokenService.generate());
		}
		thisUserDvo.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		return create(thisUserDvo, true, false);
	}

	@Transactional
	@Override
	public void delete(UserDvo userDvo, boolean flushOnFinish) {
		userRepository.delete(UserConverter.getObjectEntity(userDvo, false));
	}

	@Override
	public UserDvo update(UserDvo userDvo, boolean flushOnFinish) {
		User updatedUserDvo = userRepository.save(UserConverter.getObjectEntity(userDvo, true));
		if (flushOnFinish) {
			userRepository.flush();
		}
		return UserConverter.getObjectView(updatedUserDvo, true);
	}

	@Transactional
	@Override
	public UserDvo create(UserDvo userDvo, boolean lazy, boolean flushOnFinish) {
		return UserConverter.getObjectView(userRepository.save(UserConverter.getObjectEntity(userDvo, lazy)), lazy);
	}

	@Override
	public List<UserDvo> getAllsDvo(boolean lazy) {
		List<User> allUsersList = userRepository.findAll();

		if (Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<>(ArtroponetConstants.ZERO);
		}

		List<UserDvo> result = new ArrayList<>(ArtroponetConstants.ZERO);
		for (User u : allUsersList) {
			result.add(UserConverter.getObjectView(u, false));
		}
		return result;
	}

	@Override
	public List<User> getAllsEntity(boolean lazy) {
		List<User> allUsersList = userRepository.findAll();

		if (Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<>(ArtroponetConstants.ZERO);
		}

		return allUsersList;
	}

}
