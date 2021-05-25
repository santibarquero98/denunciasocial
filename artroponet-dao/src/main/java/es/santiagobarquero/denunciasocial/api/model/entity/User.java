package es.santiagobarquero.denunciasocial.api.model.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.LogAction;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.denunciasocial.api.dvo.GalleryDvo;
import es.santiagobarquero.denunciasocial.api.dvo.TarantulaDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
/**
 * User entity persist info about a user
 * 
 * @author santi
 * User.java
 */

@Entity
@Table(name = "tb_users")
public class User implements IEntity<UserDvo, User> {

	public User() {
		// empty constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long id;

	@Column(name = "USERNAME", length = 50)
	private String username;

	@Column(name = "PASSWORD", length = 100)
	private String password;

	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "ACTIVE", length = 2)
	private int active;
	
	@Column(name = "DAT_UP", nullable = false)
	@Temporal(TemporalType.TIME)
	private Date datUp;

	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TOKEN")
	private Token token;
	
	@OneToMany(mappedBy = "idTarantula", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Tarantula> tarantulas;
	
	@OneToMany(mappedBy = "idGallery", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Gallery> galleries;

	public List<Tarantula> getTarantulas() {
		return tarantulas;
	}

	public void setTarantulas(List<Tarantula> tarantulas) {
		this.tarantulas = tarantulas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Date getDatUp() {
		return datUp;
	}

	public void setDatUp(Date datUp) {
		this.datUp = datUp;
	}

	public List<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}

	@Override
	public UserDvo getObjectView(boolean lazy) {
		UserDvo userDvo = new UserDvo();
		userDvo.setId(this.getId());
		userDvo.setPassword(this.password);
		userDvo.setUsername(this.username);
		userDvo.setName(this.name);
		userDvo.setActive(this.active);
		try {
			Date datUp = getDatUp();
			if(datUp != null) {
				userDvo.setDatUp(Utilities.dateToString(datUp, ArtroponetConstants.STANDARD_PROJECT_DATE));
			}
		} catch (ParseException e) {
			Logger logger = LogAction.getLogger(User.class);
			logger.info(String.format("Error to convert stringToDate: -> %s", e.getLocalizedMessage()), e);
			logger = null;
		}
		
		if(lazy) {
			Token token = getToken();
			if(token != null) {
				userDvo.setTokenDvo(token.getObjectView(false));
			}
			
			List<Tarantula> tarantulas = getTarantulas();
			if(!Utilities.isNullOrEmpty(tarantulas)) {
				List<TarantulaDvo> tarantulasDvo = new ArrayList<>(ArtroponetConstants.ZERO);
				for(Tarantula t : tarantulas) {
					tarantulasDvo.add(t.getObjectView(false));
				}
				userDvo.setTarantulasDvo(tarantulasDvo);
			}
			
			List<Gallery> galleries = getGalleries();
			if(!Utilities.isNullOrEmpty(galleries)) {
				List<GalleryDvo> galleriesDvo = new ArrayList<>(ArtroponetConstants.ZERO);
				for(Gallery g : galleries) {
					galleriesDvo.add(g.getObjectView(false));
				}
				userDvo.setGalleriesDvo(galleriesDvo);
			}
			
		}
		return userDvo;
	}

	@Override
	public User createNew() {
		return new User();
	}

}
