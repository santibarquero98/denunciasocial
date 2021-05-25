package es.santiagobarquero.atroponet.test.services;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.atroponet.test.helpers.TokenHelper;
import es.santiagobarquero.atroponet.test.helpers.UserHelper;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;
import es.santiagobarquero.denunciasocial.api.service.IUserService;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
public class TokenServiceTest extends TestCase implements ITokenServiceTest {

	@MockBean
	private TokenRepository tokenRepository;
	
	@MockBean
	private IUserService userService;

	private TokenHelper tokenHelper;
	private UserHelper userHelper;

	@Before
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
		TokenDvo tokenDvo = tokenRepository.save(token).getObjectView(false);
		
		assertEquals(uuid, tokenDvo.getUuidToken());
		
	}

	@Override
	@Test
	public void checkTokenUserRelation() {
		String uuid = UUID.randomUUID().toString();
		Token mockToken = new Token(1L, uuid, userHelper.getMockedObjectEntity(false));
		Mockito.when(tokenRepository.getTokenByUUID(Matchers.anyString())).thenReturn(mockToken);
		UserDvo mockUserDvo = new UserDvo();
		mockUserDvo.setTokenDvo(mockToken.getObjectView(false));
		Mockito.when(userService.findUserByUsername(Matchers.anyString())).thenReturn(mockUserDvo);
		
		String uuidToken = uuid;
		String username = "eric2000";
		Token t = tokenRepository.getTokenByUUID(uuidToken);
		UserDvo uDvo = userService.findUserByUsername(username);
		TokenDvo tDvo = t.getObjectView(false);
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
