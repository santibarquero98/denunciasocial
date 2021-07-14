package es.santiagobarquero.artroponet.dvo.converters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.LogAction;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.model.entity.Gallery;
import es.santiagobarquero.artroponet.model.entity.Tarantula;
import es.santiagobarquero.artroponet.model.entity.Token;
import es.santiagobarquero.artroponet.model.entity.User;
import es.santiagobarquero.artroponet.resources.dvo.GalleryDvo;
import es.santiagobarquero.artroponet.resources.dvo.TarantulaDvo;
import es.santiagobarquero.artroponet.resources.dvo.UserDvo;

public final class UserConverter {
	
	Logger logger = LogAction.getLogger(UserConverter.class);
	
	private UserConverter() {
		super();
	}
	
	public static UserDvo getObjectView(User user, boolean lazy) {
		UserDvo userDvo = new UserDvo();
		userDvo.setId(user.getId());
		userDvo.setPassword(user.getPassword());
		userDvo.setUsername(user.getUsername());
		userDvo.setName(user.getName());
		userDvo.setActive(user.getActive());
		try {
			Date datUp = user.getDatUp();
			if(datUp != null) {
				userDvo.setDatUp(Utilities.dateToString(datUp, ArtroponetConstants.STANDARD_PROJECT_DATE));
			}
		} catch (ParseException e) {
			Logger logger = LogAction.getLogger(User.class);
			logger.error(String.format("Error to convert stringToDate: -> %s", e.getLocalizedMessage()), e);
			logger = null;
		}
		
		if(lazy) {
			Token token = user.getToken();
			if(token != null) {
				userDvo.setTokenDvo(TokenConverter.getObjectView(token, false));
			}
			
			List<Tarantula> tarantulas = user.getTarantulas();
			if(!Utilities.isNullOrEmpty(tarantulas)) {
				List<TarantulaDvo> tarantulasDvo = new ArrayList<>(ArtroponetConstants.ZERO);
				for(Tarantula t : tarantulas) {
					tarantulasDvo.add(TarantulaConverter.getObjectView(t, false));
				}
				userDvo.setTarantulasDvo(tarantulasDvo);
			}
			
			List<Gallery> galleries = user.getGalleries();
			if(!Utilities.isNullOrEmpty(galleries)) {
				List<GalleryDvo> galleriesDvo = new ArrayList<>(ArtroponetConstants.ZERO);
				for(Gallery g : galleries) {
					galleriesDvo.add(GalleryConverter.getObjectView(g, false));
				}
				userDvo.setGalleriesDvo(galleriesDvo);
			}
			
		}
		return userDvo;
	}

	public static User getObjectEntity(UserDvo userDvo, boolean lazy) {
		User user = new User();
		user.setActive(userDvo.getActive());
		try {
			user.setDatUp(Utilities.stringToDate(userDvo.getDatUp(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		} catch (ParseException e) {
			Logger logger = LogAction.getLogger(User.class);
			logger.error(String.format("Error to convert stringToDate: -> %s", e.getLocalizedMessage()), e);
			logger = null;
		}
		user.setId(userDvo.getId());
		user.setName(userDvo.getName());
		user.setPassword(userDvo.getPassword());
		user.setUsername(userDvo.getUsername());
		if(lazy) {
			user.setGalleries(GalleryConverter.getObjectEntity(userDvo.getGalleriesDvo(), false));
			user.setTarantulas(TarantulaConverter.getObjectEntity(userDvo.getTarantulasDvo(), false));
			user.setToken(TokenConverter.getObjectEntity(userDvo.getTokenDvo(), false));
		}
		return user;
	}
}
