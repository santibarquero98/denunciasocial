package es.santiagobarquero.atroponet.test.services;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.service.UserService;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.FailLoginException;

public class UserServiceTest extends UserService {

	private TokenServiceTest tokenServiceTest;
	
	@Before
	public void setUp() {
		tokenServiceTest = new TokenServiceTest();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	@Override
	public TokenDvo doLogin(String username, String password) throws FailLoginException {
		UserDvo userDvo = findUserByUsername(username);
		if (userDvo == null) {
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}

		boolean okCredentials = BCrypt.checkpw(password, userDvo.getPassword());

		if (okCredentials) {
			TokenDvo oldToken = userDvo.getTokenDvo();
			TokenDvo newToken = tokenServiceTest.generate();
			userDvo.setTokenDvo(newToken);
			userDvo = update(userDvo, true);
			tokenServiceTest.delete(oldToken);
			return userDvo.getTokenDvo();
		} else {
			throw new FailLoginException(HttpStatus.NO_CONTENT);
		}
	}

}
