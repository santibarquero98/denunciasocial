package es.santiagobarquero.denunciasocial.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;
/**
 * User entity persist info about a user
 * 
 * @author santi
 * User.java
 */

@Entity
@Table(name = "users")
public class User implements IEntity<UserDvo, User> {

	public User() {
		// empty constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "password", length = 100)
	private String password;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "active", length = 2)
	private int active;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_token")
	private Token token;

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

	@Override
	public UserDvo getObjectView(boolean lazy) {
		UserDvo userDvo = new UserDvo();
		userDvo.setPassword(this.password);
		userDvo.setUsername(this.username);
		userDvo.setName(this.name);
		userDvo.setActive(this.active);
		if(lazy) {
			if(!Utilities.isNull(getToken())) {
				userDvo.setTokenDvo(getToken().getObjectView(false));
			}
		}
		return userDvo;
	}

	@Override
	public User createNew() {
		return new User();
	}

}
