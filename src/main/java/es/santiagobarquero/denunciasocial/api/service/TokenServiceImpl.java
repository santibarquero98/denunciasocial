package es.santiagobarquero.denunciasocial.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;

@Service("tokenService")
public class TokenServiceImpl implements ITokenService {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TokenRepository tokenRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private UserServiceImpl userService;
	
	public TokenServiceImpl() {
		super();
	}

	public TokenServiceImpl(TokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
	}
	
	@Override
	public TokenDvo generate(){
		String uuid = UUID.randomUUID().toString();
		Token token = new Token();
		token.setUuidToken(uuid);
		return tokenRepository.save(token).getObjectView(false);
	}
	
	@Override
	public boolean checkTokenUserRelation(String uuidToken, String username) {
		Token t = tokenRepository.getTokenByUUID(uuidToken);
		if(t == null) return false;
		UserDvo uDvo = userService.findUserByUsername(username);
		if(uDvo == null) return false;
		TokenDvo tDvo = t.getObjectView(false);
		return uDvo.getTokenDvo().getUuidToken().equals(tDvo.getUuidToken()) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
		
	}
	
	@Override
	public void delete(TokenDvo tokenDvo, boolean flushOnFinish) {
		tokenRepository.delete(tokenDvo.getEntityObject(false));
	}

	@Override
	public List<TokenDvo> getAllsDvo(boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Token> getAllsEntity(boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenDvo create(TokenDvo tokenDvo, boolean lazy, boolean flushOnFinish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TokenDvo update(TokenDvo tokenDvo, boolean flushOnFinish) {
		// TODO Auto-generated method stub
		return null;
	}

}
