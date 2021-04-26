package es.santiagobarquero.atroponet.test;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.denunciasocial.ApplicationCoreRunner;
import es.santiagobarquero.denunciasocial.api.controller.auth.UserController;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;
import es.santiagobarquero.denunciasocial.api.model.repository.UserRepository;
import junit.framework.TestCase;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationCoreRunner.class)
public class ArtroponetTest extends TestCase {

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TokenRepository tokenRepository;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void setUp() {
		if(userController == null) {
			userController = new UserController();
		}
	}
	
	@Test
	public void testLogin() {
		User user = new User();
		user.setActive(1);
		user.setUsername("root");
		user.setId(30L);
		user.setName("Administrador");
		user.setPassword("clave");
		user.setToken(new Token());
		when(userRepository.getUserByUsername("")).thenReturn(user);
		UserDvo userDvo = new UserDvo();
		userDvo.setPassword("clave");
		userDvo.setTokenDvo(new TokenDvo());
		ResponseEntity<TokenDvo> responseEntity = userController.doLogin(userDvo);
		TokenDvo tokenDvo = responseEntity.getBody();
		String expectedData = "";
		assertEquals(expectedData, tokenDvo.getUuidToken());
	}
	
	

}
