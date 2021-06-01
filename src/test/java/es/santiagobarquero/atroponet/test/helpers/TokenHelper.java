package es.santiagobarquero.atroponet.test.helpers;

import java.util.List;
import java.util.UUID;

import es.santiagobarquero.artroponet.model.entity.Token;
import es.santiagobarquero.artroponet.resources.dvo.TokenDvo;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;

public final class TokenHelper implements JUnitHelper<Token, TokenDvo> {

	private final static UserHelper USER_HELPER = new UserHelper();
	
	public TokenHelper() {
		super();
	}
	
	@Override
	public Token getMockedObjectEntity(boolean lazy) {
		Token t = new Token();
		t.setId(1L);
		t.setUuidToken(UUID.randomUUID().toString());
		if(lazy) {
			t.setUser(USER_HELPER.getMockedObjectEntity(false));
		}
		return t;
	}

	@Override
	public TokenDvo getMockedObjectDvo(boolean lazy) {
		TokenDvo tDvo = new TokenDvo();
		tDvo.setId(1L);
		tDvo.setUuidToken(UUID.randomUUID().toString());
		if(lazy) {
			tDvo.setUserDvo(USER_HELPER.getMockedObjectDvo(lazy));
		}
		return tDvo;
	}

	@Override
	public List<Token> getMockedListEntity(boolean lazy, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TokenDvo> getMockedListDvo(boolean lazy, int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
