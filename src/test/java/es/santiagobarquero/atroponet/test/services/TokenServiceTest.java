package es.santiagobarquero.atroponet.test.services;

import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.artroponet.dvo.converters.TokenConverter;
import es.santiagobarquero.artroponet.model.entity.Token;
import es.santiagobarquero.artroponet.model.repository.TokenRepository;
import es.santiagobarquero.artroponet.resources.dvo.TokenDvo;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;
import es.santiagobarquero.artroponet.service.IUserService;
import es.santiagobarquero.artroponet.service.UserServiceImpl;
import es.santiagobarquero.atroponet.test.helpers.TokenHelper;
import es.santiagobarquero.atroponet.test.helpers.UserHelper;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class TokenServiceTest extends TestCase implements ITokenServiceTest {

	@MockBean
	private TokenRepository tokenRepository;
	
	private UserServiceImpl userService = new UserServiceImpl();

	private TokenHelper tokenHelper;
	private UserHelper userHelper;

	@BeforeEach
	public void setUp() {
		tokenHelper = new TokenHelper();
		userHelper = new UserHelper();
	}

	@Override
	@Test
	public void delete() {
		Mockito.doNothing().when(tokenRepository).delete((Token) Matchers.any(Token.class));
		tokenRepository.delete(tokenHelper.getMockedObjectEntity(false));
	}

	@Override
	@Test
	public void generate() {
		String uuid = UUID.randomUUID().toString();
		Token mockToken = new Token(1L, uuid, userHelper.getMockedObjectEntity(false));
		Mockito.when(tokenRepository.save(Matchers.any(Token.class))).thenReturn(mockToken);
		
		Token token = new Token();
		token.setUuidToken(uuid);
		TokenDvo tokenDvo = TokenConverter.getObjectView(token, false);
	
		assertEquals(uuid, tokenDvo.getUuidToken());
		
	}

	@Override
	@Test
	public void checkTokenUserRelation() {
		String uuid = UUID.randomUUID().toString();
		Token mockToken = new Token(1L, uuid, userHelper.getMockedObjectEntity(false));
		Mockito.when(tokenRepository.getTokenByUUID(Matchers.anyString())).thenReturn(mockToken);
		UserDvo mockUserDvo = new UserDvo();
		mockUserDvo.setTokenDvo(TokenConverter.getObjectView(mockToken, false));
		Mockito.when(userService.findUserByUsername(Matchers.anyString())).thenReturn(mockUserDvo);
		
		String uuidToken = uuid;
		String username = "eric2000";
		Token t = tokenRepository.getTokenByUUID(uuidToken);
		UserDvo uDvo = userService.findUserByUsername(username);
		TokenDvo tDvo = TokenConverter.getObjectView(t, false);
		assertEquals(uDvo.getTokenDvo().getUuidToken(), tDvo.getUuidToken());
		
	}

	@Override
	public void getAllsDvo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAllsEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
