package es.santiagobarquero.denunciasocial.api.dvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.User;

/**
 * UserDvo class defines the POJO from User entity (User Data View Object)
 * 
 * @author santi
 *
 */

public class UserDvo implements IDvo<User, UserDvo> {
	
	@JsonIgnore
	private Long id;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("active")
	private int active;
	
	@JsonProperty("token")
	private TokenDvo tokenDvo;

	public UserDvo() {
		// empty constructor
	}

	
	/*
	 * GETTERS AND SETTERS
	 */
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public TokenDvo getTokenDvo() {
		return tokenDvo;
	}

	public void setTokenDvo(TokenDvo tokenDvo) {
		this.tokenDvo = tokenDvo;
	}

	/*
	 * METHODS
	 */

	@Override
	public User getEntityObject(boolean lazy) {
		User user = new User();
		user.setId(this.id);
		user.setUsername(this.username);
		user.setPassword(this.password);
		user.setName(this.name);
		user.setActive(this.active);
		if(lazy) {
			user.setToken(getTokenDvo().getEntityObject(false));
		}
		return user;
	}

	@Override
	public UserDvo createNew() {
		return new UserDvo();
	}

}
