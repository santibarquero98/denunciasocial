package es.santiagobarquero.atroponet.test.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.atroponet.test.helpers.TokenHelper;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class TokenServiceTest extends TestCase {

	@MockBean
	private TokenRepository tokenRepository;
	
	private TokenHelper tokenHelper;
	
	@Before
	public void setUp() {
		tokenHelper = new TokenHelper();
	}
	
	public TokenDvo generate() {
		return null;
	}
	
	@Test
	public void delete() {
		Mockito.doNothing().when(tokenRepository).delete((Token) Matchers.any(Token.class));
		tokenRepository.delete(tokenHelper.getMockedObjectEntity(false));
	}
	
}
