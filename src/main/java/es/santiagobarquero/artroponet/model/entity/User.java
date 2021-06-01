package es.santiagobarquero.artroponet.model.entity;

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
/**
 * User entity persist info about a user
 * 
 * @author santi
 * User.java
 */

@Entity
@Table(name = "tb_users")
public class User {
//implements IEntity<UserDvo, User> {

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


//
//	@Override
//	public User createNew() {
//		return new User();
//	}

}
