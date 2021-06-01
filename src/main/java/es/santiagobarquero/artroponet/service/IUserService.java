package es.santiagobarquero.artroponet.service;

import java.text.ParseException;
import java.util.List;

import es.santiagobarquero.artroponet.auxiliary.exceptions.FailLoginException;
import es.santiagobarquero.artroponet.model.entity.User;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;

public interface IUserService {

	UserDvo doLogin(String username, String password) throws FailLoginException;
	
	UserDvo createNewUser(UserDvo userDvo, boolean generateToken) throws ParseException;
	
	UserDvo findUserByUsername(String username);
	
	UserDvo findIt(Long pkUserLong);
	
	List<UserDvo> getAllsDvo(boolean lazy);
	
	List<User> getAllsEntity(boolean lazy);
	
	UserDvo create(UserDvo userDvo, boolean lazy, boolean flushOnFinish);
	
	UserDvo update(UserDvo userDvo, boolean flushOnFinish);
	
	void delete(UserDvo userDvo, boolean flushOnFinish);
	
}
