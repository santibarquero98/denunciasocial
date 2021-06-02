package es.santiagobarquero.artroponet.dvo.converters;

import es.santiagobarquero.artroponet.model.entity.Token;
import es.santiagobarquero.artroponet.resources.dvo.TokenDvo;

public final class TokenConverter {

	private TokenConverter() {
		super();
	}
	
	public static TokenDvo getObjectView(Token token, boolean lazy) {
		TokenDvo tokenDvo = TokenDvo.createNewInstance();
		tokenDvo.setId(token.getId());
		tokenDvo.setUuidToken(token.getUuidToken());
		if(lazy) {
			tokenDvo.setUserDvo(UserConverter.getObjectView(token.getUser(), false));
		}
		return tokenDvo;
	}

	public static Token getObjectEntity(TokenDvo tokenDvo, boolean lazy) {
		Token token = Token.createNewInstance();
		token.setId(tokenDvo.getId());
		token.setUuidToken(tokenDvo.getUuidToken());
		if(lazy) {
			token.setUser(UserConverter.getObjectEntity(tokenDvo.getUserDvo(), false));
		}
		return token;
	}
	
}
