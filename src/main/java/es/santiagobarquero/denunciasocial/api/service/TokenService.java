package es.santiagobarquero.denunciasocial.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;

@Service
public class TokenService {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TokenRepository tokenRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private UserServiceImpl userService;
	
	public TokenDvo generate(){
		String uuid = UUID.randomUUID().toString();
		Token token = new Token();
		token.setUuidToken(uuid);
		return tokenRepository.save(token).getObjectView(false);
	}
	
	public boolean checkTokenUserRelation(String uuidToken, String username) {
		Token t = tokenRepository.getTokenByUUID(uuidToken);
		if(t == null) return false;
		UserDvo uDvo = userService.findUserByUsername(username);
		if(uDvo == null) return false;
		TokenDvo tDvo = t.getObjectView(false);
		return uDvo.getTokenDvo().getUuidToken().equals(tDvo.getUuidToken()) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
		
	}
	
	public void delete(TokenDvo tokenDvo) {
		tokenRepository.delete(tokenDvo.getEntityObject(false));
	}

}
