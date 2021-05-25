package es.santiagobarquero.denunciasocial.api.service;

import java.text.ParseException;
import java.util.List;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.FailLoginException;

public interface IUserService {

	TokenDvo doLogin(String username, String password) throws FailLoginException;
	
	UserDvo createNewUser(UserDvo userDvo, boolean generateToken) throws ParseException;
	
	UserDvo findUserByUsername(String username);
	
	UserDvo findIt(Long pkUserLong);
	
	List<UserDvo> getAllsDvo(boolean lazy);
	
	List<User> getAllsEntity(boolean lazy);
	
	UserDvo create(UserDvo userDvo, boolean lazy, boolean flushOnFinish);
	
	UserDvo update(UserDvo userDvo, boolean flushOnFinish);
	
	void delete(UserDvo userDvo, boolean flushOnFinish);
	
}
