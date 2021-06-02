package es.santiagobarquero.artroponet.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tb_tokens")
public class Token {
//implements IEntity<TokenDvo, Token> {

	public Token() {
		super();
	}
	
	public Token(Long id, String uuidToken, User user) {
		super();
		this.id = id;
		this.uuidToken = uuidToken;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TOKEN")
	private Long id;
	
	@Column(name = "UUID_TOKEN", length = 100)
	private String uuidToken;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
	private User user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuidToken() {
		return uuidToken;
	}

	public void setUuidToken(String uuidToken) {
		this.uuidToken = uuidToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Token createNewInstance() {
		return new Token();
	}

//	@Override
//	public TokenDvo getObjectView(boolean lazy) {
//		TokenDvo tDvo = new TokenDvo();
//		tDvo.setId(getId());
//		tDvo.setUuidToken(getUuidToken());
//		return tDvo;
//	}
//
//	@Override
//	public Token createNew() {
//		return new Token();
//	}

}
