package es.santiagobarquero.denunciasocial.api.service;

import java.util.List;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;

public interface ITokenService {

	TokenDvo generate();
	
	boolean checkTokenUserRelation(String uuidToken, String username);
	
	List<TokenDvo> getAllsDvo(boolean lazy);
	
	List<Token> getAllsEntity(boolean lazy);
	
	TokenDvo create(TokenDvo tokenDvo, boolean lazy, boolean flushOnFinish);
	
	TokenDvo update(TokenDvo tokenDvo, boolean flushOnFinish);
	
	void delete(TokenDvo tokenDvo, boolean flushOnFinish);
	
}
