package es.santiagobarquero.atroponet.test.helpers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.model.entity.User;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;

public class UserHelper implements JUnitHelper<User, UserDvo> {

	private final static TokenHelper TOKEN_HELPER = new TokenHelper();
	private final static GalleryHelper GALLERY_HELPER = new GalleryHelper();
	private final static TarantulaHelper TARANTULA_HELPER = new TarantulaHelper();
	
	public UserHelper() {
		super();
	}
	
	@Override
	public User getMockedObjectEntity(boolean lazy) {
		int defaultSize = 5;
		User u = new User();
		u.setActive(1);
		u.setDatUp(new Date());
		u.setId(1L);
		u.setName("Eric");
		u.setPassword(Utilities.encryptPassword("pwd"));
		u.setUsername("eric2000");
		if(lazy) {
			u.setToken(TOKEN_HELPER.getMockedObjectEntity(false));
			u.setGalleries(GALLERY_HELPER.getMockedListEntity(false, defaultSize));
			u.setTarantulas(TARANTULA_HELPER.getMockedListEntity(false, defaultSize));
		}
		return u;
	}

	@Override
	public UserDvo getMockedObjectDvo(boolean lazy) {
		int defaultSize = 5;
		UserDvo uDvo = new UserDvo();
		uDvo.setActive(1);
		try {
			uDvo.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		} catch (ParseException e) {
			// TODO: log error
		}
		uDvo.setId(1L);
		uDvo.setName("Eric");
		uDvo.setPassword("pwd");
		uDvo.setUsername("eric2000");
		if(lazy) {
			uDvo.setTokenDvo(TOKEN_HELPER.getMockedObjectDvo(false));
			uDvo.setGalleriesDvo(GALLERY_HELPER.getMockedListDvo(lazy, defaultSize));
			uDvo.setTarantulasDvo(TARANTULA_HELPER.getMockedListDvo(lazy, defaultSize));
		}
		return uDvo;
	}

	@Override
	public List<User> getMockedListEntity(boolean lazy, int size) {
		List<User> result = new ArrayList<User>(ArtroponetConstants.ZERO);
		for(int i = 0; i < size; i++) {
			User u = new User();
			u.setActive(1);
			u.setDatUp(new Date());
			u.setId(Long.valueOf(i));
			u.setName("Eric " + i);
			u.setPassword("pwd" + i);
			u.setUsername("eric2000" + i);
			if(lazy) {
				u.setGalleries(GALLERY_HELPER.getMockedListEntity(false, size));
				u.setTarantulas(TARANTULA_HELPER.getMockedListEntity(false, size));
				u.setToken(TOKEN_HELPER.getMockedObjectEntity(false));
			}
			result.add(u);
		}
		return result;
	}

	@Override
	public List<UserDvo> getMockedListDvo(boolean lazy, int size) {
		List<UserDvo> result = new ArrayList<UserDvo>(ArtroponetConstants.ZERO);
		for(int i = 0; i < size; i++) {
			UserDvo uDvo = new UserDvo();
			uDvo.setActive(1);
			try {
				uDvo.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
			} catch (ParseException e) {
				// TODO: implementar log
			}
			uDvo.setId(Long.valueOf(i));
			uDvo.setName("Eric " + i);
			uDvo.setPassword("pwd" + i);
			uDvo.setUsername("eric2000" + i);
			if(lazy) {
				uDvo.setGalleriesDvo(GALLERY_HELPER.getMockedListDvo(false, size));
				uDvo.setTarantulasDvo(TARANTULA_HELPER.getMockedListDvo(false, size));
				uDvo.setTokenDvo(TOKEN_HELPER.getMockedObjectDvo(false));
			}
			result.add(uDvo);
		}
		return result;
	}

}
