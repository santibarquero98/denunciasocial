package es.santiagobarquero.denunciasocial.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;

@Service
public class TokenService {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TokenRepository tokenRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	public TokenDvo generate(){
		String uuid = UUID.randomUUID().toString();
		Token token = new Token();
		token.setUuidToken(uuid);
		TokenDvo tokenDvo = tokenRepository.save(token).getObjectView(false);
		return tokenDvo;
	}

}
