package es.santiagobarquero.denunciasocial.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.UserRepository;
import es.santiagobarquero.denunciasocial.auxiliary.DenunciasocialConstants;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;

@Service
public class UserService implements ServiceInterface<UserDvo, User> {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private UserRepository userRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TokenService tokenService;
	
	
	/*
	 * PERSIST METHODS
	 */
	@Transactional
	public UserDvo createNewUser(UserDvo userDvo, boolean generateToken) {
		UserDvo thisUserDvo = userDvo;
		boolean thisGenerateToken = generateToken;
		if(thisUserDvo == null || !Utilities.isNull(userRepository.getUserByUsername(thisUserDvo.getUsername()))) {
			return thisUserDvo;
		}
		thisUserDvo.setActive(thisUserDvo.getActive());
		thisUserDvo.setPassword(Utilities.encryptPassword(thisUserDvo.getPassword()));
		if(thisGenerateToken) {
			thisUserDvo.setTokenDvo(tokenService.generate());
		}
		return create(thisUserDvo, true);
	}
	
	@Transactional
	@Override
	public void delete(UserDvo userDvo) {
		userRepository.delete(userDvo.getEntityObject(false));
	}
	
	@Transactional
	@Override
	public UserDvo update(UserDvo userDvo) {
		return userRepository.save(userDvo.getEntityObject(false)).getObjectView(false);
	}
	
	@Transactional
	@Override
	public UserDvo create(UserDvo userDvo, boolean lazy) {
		return userRepository.save(userDvo.getEntityObject(lazy)).getObjectView(lazy);
	}
	
	/*
	 * READ/GET INFO METHODS
	 */
	
	@Override
	public List<UserDvo> getAllsDvo(boolean lazy) {
		List<User> allUsersList = userRepository.findAll();
		
		if(Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<UserDvo>(DenunciasocialConstants.ZERO);
		}
		
		List<UserDvo> result = new ArrayList<UserDvo>(DenunciasocialConstants.ZERO);
		for(User u : allUsersList) {
			result.add(u.getObjectView(lazy));
		}
		return result;
	}
	
	@Override
	public List<User> getAllsEntity() {
		List<User> allUsersList = userRepository.findAll();
		
		if(Utilities.isNullOrEmpty(allUsersList)) {
			return new ArrayList<User>(DenunciasocialConstants.ZERO);
		}
		
		return allUsersList;
	}
	
	public UserDvo findUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		if(Utilities.isNull(user)) {
			return null;
		}
		
		return user.getObjectView(true);
	}

}
