package es.santiagobarquero.atroponet.test.services;

import static org.mockito.Matchers.any;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.auxiliary.exceptions.FailLoginException;
import es.santiagobarquero.artroponet.dvo.converters.UserConverter;
import es.santiagobarquero.artroponet.model.entity.User;
import es.santiagobarquero.artroponet.model.repository.TokenRepository;
import es.santiagobarquero.artroponet.model.repository.UserRepository;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;
import es.santiagobarquero.artroponet.service.TokenServiceImpl;
import es.santiagobarquero.artroponet.service.UserServiceImpl;
import es.santiagobarquero.atroponet.test.helpers.TokenHelper;
import es.santiagobarquero.atroponet.test.helpers.UserHelper;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class UserServiceTest extends TestCase {

	// Helpers (START)
	private UserHelper userHelper;
	private TokenHelper tokenHelper;
	// Helpers (END)

	
	// Mocks (START)
	private TokenRepository tokenRepositoryMock = Mockito.mock(TokenRepository.class);
	private UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);
	private TokenServiceImpl tokenServiceMock = Mockito.mock(TokenServiceImpl.class);
	private UserServiceImpl userServiceMock = Mockito.mock(UserServiceImpl.class);
	// Mocks (END)
	
	// SERVICE TO TEST (START)
	private TokenServiceImpl tokenService = new TokenServiceImpl(tokenRepositoryMock, userServiceMock);
	private UserServiceImpl userService = new UserServiceImpl(userRepositoryMock, tokenServiceMock);
	// SERVICE TO TEST (END)
	
	// TODO: implementer the Matchers with any(Object.class)
	@Before
	public void setUp() {
		userHelper = new UserHelper();
		tokenHelper = new TokenHelper();
		execMocks();
		
		
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

	
	private void execMocks() {
		Mockito.when(userRepositoryMock.getUserByUsername(any(String.class))).thenReturn(userHelper.getMockedObjectEntity(true));
		Mockito.when(tokenServiceMock.generate()).thenReturn(tokenHelper.getMockedObjectDvo(true));
		
	}


	@Test
	public void doLogin() {
		String username = "Eric";
		String pwd = "pwd";
		UserDvo userDvo = null;
		try {
			userDvo = userService.doLogin(username, pwd);
		} catch (FailLoginException e) {
			fail("Error en el login");
		}
		assertEquals(username, userDvo.getUsername());
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
		User userFinded = userRepositoryMock.getOne(pk);
		UserDvo userDvo = UserConverter.getObjectView(userFinded, false);
		assertEquals(pk, userDvo.getId());

	}

	
	@Test
	public void findUserByUsername() {
		String username = "Eric";
		User user = userRepositoryMock.getUserByUsername(username);
		assertNotNull(user);

	}

	
	@Test
	public void getAllsDvo() {
		int sizeList = 10;
		List<User> allMockedUsers = userRepositoryMock.findAll();
		List<UserDvo> allMockedUsersDvo = new ArrayList<UserDvo>(ArtroponetConstants.ZERO);
		for (User uDvo : allMockedUsers) {
			allMockedUsersDvo.add(UserConverter.getObjectView(uDvo, false));
		}
		int expectedSize = sizeList;
		assertEquals(expectedSize, allMockedUsersDvo.size());

	}

	
	@Test
	public void getAllsEntity() {
		int sizeList = 10;
		List<User> allMockedUsers = userRepositoryMock.findAll();
		int expectedSize = sizeList;
		assertEquals(expectedSize, allMockedUsers.size());
	}

	
//	@Test
//	public void create() {
//		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
//		userDvo = userRepositoryMock.save(userDvo.getEntityObject(false)).getObjectView(false);
//		assertNotNull(userDvo);
//
//	}
//
//	
//	@Test
//	public void update() {
//		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
//		UserDvo updatedUserDvo = userRepositoryMock.save(userDvo.getEntityObject(true)).getObjectView(true);
//		assertEquals(userDvo.getId(), updatedUserDvo.getId());
//	}
//
//	
//	@Test
//	public void delete() {
//		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
//		userRepositoryMock.delete(userDvo.getEntityObject(false));
//	}

}
