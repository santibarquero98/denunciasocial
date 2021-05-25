package es.santiagobarquero.atroponet.test.services;

import static org.mockito.Matchers.any;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.atroponet.test.helpers.TokenHelper;
import es.santiagobarquero.atroponet.test.helpers.UserHelper;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.TokenRepository;
import es.santiagobarquero.denunciasocial.api.model.repository.UserRepository;
import es.santiagobarquero.denunciasocial.api.service.ITokenService;
import es.santiagobarquero.denunciasocial.api.service.IUserService;
import es.santiagobarquero.denunciasocial.api.service.TokenServiceImpl;
import es.santiagobarquero.denunciasocial.api.service.UserServiceImpl;
import es.santiagobarquero.denunciasocial.auxiliary.ArtroponetConstants;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.FailLoginException;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class UserServiceTest extends TestCase {

	// Helpers (START)
	private UserHelper userHelper;
	private TokenHelper tokenHelper;
	// Helpers (END)

	private TokenRepository tokenRepository = Mockito.mock(TokenRepository.class);
	private UserRepository userRepository = Mockito.mock(UserRepository.class);
	
	
	private ITokenService tokenService = new TokenServiceImpl(tokenRepository);
	
	@Autowired
	private IUserService userService;
//	 = new UserServiceImpl(userRepository);

//	@Autowired
//	private ITokenService tokenService;

	// TODO: implementer the Matchers with any(Object.class)
	@BeforeEach
	public void setUp() {
		userHelper = new UserHelper();
		tokenHelper = new TokenHelper();
		
		
//		Mockito.when(userRepository.getUserByUsername(any(String.class))).thenReturn(userHelper.getMockedObjectEntity(false));
//		Mockito.when(userService.findUserByUsername(any(String.class))).thenReturn(userHelper.getMockedObjectDvo(true));
		/*
		
		Mockito.when(userService.update(Matchers.any(UserDvo.class), Matchers.anyBoolean())).thenReturn(userHelper.getMockedObjectDvo(true));
		Mockito.when(tokenService.generate()).thenReturn(tokenHelper.getMockedObjectDvo(false));
		Mockito.doNothing().when(tokenService).delete(any((TokenDvo.class)), any(Boolean.class));
		Mockito.when(userRepository.getOne(any(Long.class))).thenReturn(userHelper.getMockedObjectEntity(false));
		Mockito.when(userRepository.getUserByUsername(Matchers.anyString())).thenReturn(userHelper.getMockedObjectEntity(false));
		Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(userHelper.getMockedObjectEntity(false));
		doNothing().when(userRepository).delete(Matchers.any(User.class));
		Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(userHelper.getMockedObjectEntity(false));
		doNothing().when(userRepository).flush();
		Mockito.when(userRepository.findAll()).thenReturn(userHelper.getMockedListEntity(false, sizeList));
		Mockito.when(userRepository.findAll()).thenReturn(userHelper.getMockedListEntity(false, sizeList));
		Mockito.when(userService.create(Matchers.any(UserDvo.class), any(Boolean.class), any(Boolean.class))).thenReturn(userHelper.getMockedObjectDvo(true));
		*/
	}

	
	@Test
	public void doLogin() {

		Mockito.when(userRepository.getUserByUsername(any(String.class))).thenReturn(userHelper.getMockedObjectEntity(true));
		String username = "Eric";
		String pwd = "pwd";
		TokenDvo tokenDvo = null;
		try {
			tokenDvo = userService.doLogin(username, pwd);
		} catch (FailLoginException e) {
			fail("Error en el login");
		}
		assertEquals(username, tokenDvo.getUserDvo().getUsername());
	}

	
	@Test
	public void createNewUser() {
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		userDvo.setActive(userDvo.getActive());
		userDvo.setPassword(Utilities.encryptPassword(userDvo.getPassword()));
		userDvo.setTokenDvo(tokenService.generate());
		try {
			userDvo.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		} catch (ParseException e) {
			fail("An ParseException has been ocurred");
		}
		UserDvo userCreated = userService.create(userDvo, true, false);
		assertEquals(userDvo.getUsername(), userCreated.getUsername());
	}

	
	@Test
	public void findIt() {
		Long pk = 1L;
		User userFinded = userRepository.getOne(pk);
		UserDvo userDvo = userFinded.getObjectView(true);
		assertEquals(pk, userDvo.getId());

	}

	
	@Test
	public void findUserByUsername() {
		String username = "Eric";
		User user = userRepository.getUserByUsername(username);
		assertNotNull(user);

	}

	
	@Test
	public void getAllsDvo() {
		int sizeList = 10;
		List<User> allMockedUsers = userRepository.findAll();
		List<UserDvo> allMockedUsersDvo = new ArrayList<UserDvo>(ArtroponetConstants.ZERO);
		for (User uDvo : allMockedUsers) {
			allMockedUsersDvo.add(uDvo.getObjectView(false));
		}
		int expectedSize = sizeList;
		assertEquals(expectedSize, allMockedUsersDvo.size());

	}

	
	@Test
	public void getAllsEntity() {
		int sizeList = 10;
		List<User> allMockedUsers = userRepository.findAll();
		int expectedSize = sizeList;
		assertEquals(expectedSize, allMockedUsers.size());

	}

	
	@Test
	public void create() {
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		userDvo = userRepository.save(userDvo.getEntityObject(false)).getObjectView(false);
		assertNotNull(userDvo);

	}

	
	@Test
	public void update() {
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		UserDvo updatedUserDvo = userRepository.save(userDvo.getEntityObject(true)).getObjectView(true);
		assertEquals(userDvo.getId(), updatedUserDvo.getId());
	}

	
	@Test
	public void delete() {
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		userRepository.delete(userDvo.getEntityObject(false));
	}

}
