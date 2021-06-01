package es.santiagobarquero.artroponet.service;

import java.util.List;

import es.santiagobarquero.artroponet.model.entity.Token;
import es.santiagobarquero.artroponet.resources.dvo.TokenDvo;

public interface ITokenService {

	TokenDvo generate();
	
	boolean checkTokenUserRelation(String uuidToken, String username);
	
	List<TokenDvo> getAllsDvo(boolean lazy);
	
	List<Token> getAllsEntity(boolean lazy);
	
	TokenDvo create(TokenDvo tokenDvo, boolean lazy, boolean flushOnFinish);
	
	TokenDvo update(TokenDvo tokenDvo, boolean flushOnFinish);
	
	void delete(TokenDvo tokenDvo, boolean flushOnFinish);
	
}
