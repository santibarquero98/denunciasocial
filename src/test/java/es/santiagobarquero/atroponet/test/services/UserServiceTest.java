package es.santiagobarquero.atroponet.test.services;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.api.model.repository.UserRepository;
import es.santiagobarquero.denunciasocial.api.service.TokenService;
import es.santiagobarquero.denunciasocial.api.service.UserServiceImpl;
import es.santiagobarquero.denunciasocial.auxiliary.ArtroponetConstants;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;
import junit.framework.TestCase;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
public class UserServiceTest extends TestCase implements IUserServiceTest {

	// Helpers (START)
	private UserHelper userHelper;
	private TokenHelper tokenHelper;
	// Helpers (END)
	
	@MockBean
	private UserServiceImpl userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private TokenService tokenService;

	@Before
	public void setUp() {
		userHelper = new UserHelper();
		tokenHelper = new TokenHelper();
	}

	@Override
	@Test
	public void doLogin() {
		Mockito.when(userService.findUserByUsername(Matchers.anyString())).thenReturn(userHelper.getMockedObjectDvo(true));
		Mockito.when(userService.update(Matchers.any(UserDvo.class), Matchers.anyBoolean())).thenReturn(userHelper.getMockedObjectDvo(true));
		Mockito.when(tokenService.generate()).thenReturn(tokenHelper.getMockedObjectDvo(false));
		Mockito.doNothing().when(tokenService).delete(Matchers.any(TokenDvo.class));
		
		String username = "Eric";
		UserDvo userDvo = userService.findUserByUsername(username);
		TokenDvo oldToken = userDvo.getTokenDvo();
		TokenDvo newToken = tokenService.generate();
		userDvo.setTokenDvo(newToken);
		userDvo = userService.update(userDvo, false);
		tokenService.delete(oldToken);
		
		assertNotEquals(oldToken.getUuidToken(), userDvo.getTokenDvo().getUuidToken());
	}
	
	@Override
	@Test
	public void createNewUser() {
		Mockito.when(tokenService.generate()).thenReturn(tokenHelper.getMockedObjectDvo(false));
		Mockito.when(userService.create(Matchers.any(UserDvo.class), Matchers.anyBoolean(), Matchers.anyBoolean())).thenReturn(userHelper.getMockedObjectDvo(true));
		
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
	
	@Override
	@Test
	public void findIt() {
		Mockito.when(userRepository.getOne(Matchers.anyLong())).thenReturn(userHelper.getMockedObjectEntity(false));
		
		Long pk = 1L;
		User userFinded = userRepository.getOne(pk);
		UserDvo userDvo = userFinded.getObjectView(true);
		
		assertEquals(pk, userDvo.getId());
		
	}

	@Override
	@Test
	public void findUserByUsername() {
		Mockito.when(userRepository.getUserByUsername(Matchers.anyString())).thenReturn(userHelper.getMockedObjectEntity(false));
		
		String username = "Eric";
		User user = userRepository.getUserByUsername(username);
		
		assertNotNull(user);
		
	}

	@Override
	@Test
	public void getAllsDvo() {
		int sizeList = 10;
		
		Mockito.when(userRepository.findAll()).thenReturn(userHelper.getMockedListEntity(false, sizeList));
		
		List<User> allMockedUsers = userRepository.findAll();
		List<UserDvo> allMockedUsersDvo = new ArrayList<UserDvo>(ArtroponetConstants.ZERO);
		for(User uDvo : allMockedUsers) {
			allMockedUsersDvo.add(uDvo.getObjectView(false));
		}
		int expectedSize = sizeList;
		
		assertEquals(expectedSize, allMockedUsersDvo.size());
		
	}

	@Override
	@Test
	public void getAllsEntity() {
		int sizeList = 10;
		
		Mockito.when(userRepository.findAll()).thenReturn(userHelper.getMockedListEntity(false, sizeList));
		
		List<User> allMockedUsers = userRepository.findAll();
		int expectedSize = sizeList;
		
		assertEquals(expectedSize, allMockedUsers.size());
		
	}

	@Override
	@Test
	public void create() {
		Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(userHelper.getMockedObjectEntity(false));
		
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		userDvo = userRepository.save(userDvo.getEntityObject(false)).getObjectView(false);
		
		assertNotNull(userDvo);
		
	}

	@Override
	@Test
	public void update() {
		Mockito.when(userRepository.save(Matchers.any(User.class))).thenReturn(userHelper.getMockedObjectEntity(false));
		doNothing().when(userRepository).flush();
		
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		UserDvo updatedUserDvo = userRepository.save(userDvo.getEntityObject(true)).getObjectView(true);
		
		assertEquals(userDvo.getId(), updatedUserDvo.getId());
		
	}

	@Override
	@Test
	public void delete() {
		doNothing().when(userRepository).delete(Matchers.any(User.class));
		
		UserDvo userDvo = userHelper.getMockedObjectDvo(false);
		
		userRepository.delete(userDvo.getEntityObject(false));
	}

}
