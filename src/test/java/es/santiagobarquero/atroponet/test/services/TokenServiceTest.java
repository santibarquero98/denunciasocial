package es.santiagobarquero.atroponet.test.services;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;
import es.santiagobarquero.denunciasocial.api.service.TokenService;

public class TokenServiceTest extends TokenService {

	@MockBean
	private TokenRepository tokenRepository;
	
	@Override
	public TokenDvo generate() {
		// TODO Auto-generated method stub
		return super.generate();
	}
	
	@Override
	public void delete(TokenDvo tokenDvo) {
		Mockito.doCallRealMethod().when(tokenRepository).delete(tokenDvo.getEntityObject(false));
		tokenRepository.delete(tokenDvo.getEntityObject(false));
	}
	
}
