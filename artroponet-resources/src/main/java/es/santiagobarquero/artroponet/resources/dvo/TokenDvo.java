package es.santiagobarquero.artroponet.resources.dvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Token;

public class TokenDvo implements IDvo<Token, TokenDvo>{

	@JsonIgnore
	private Long id;
	
	@JsonProperty("uuidtoken")
	private String uuidToken;
	
	@JsonProperty("userDvo")
	private UserDvo userDvo;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUuidToken(String uuidToken) {
		this.uuidToken = uuidToken;
	}
	
	public String getUuidToken() {
		return uuidToken;
	}

	public UserDvo getUserDvo() {
		return userDvo;
	}

	public void setUserDvo(UserDvo userDvo) {
		this.userDvo = userDvo;
	}

	@Override
	public Token getEntityObject(boolean lazy) {
		Token t = new Token();
		t.setId(getId());
		t.setUuidToken(getUuidToken());
		if(lazy) {
			t.setUser(getUserDvo().getEntityObject(false));
		}
		return t;
	}

	@Override
	public TokenDvo createNew() {
		return new TokenDvo();
	}

	@Override
	public String toString() {
		return "TokenDvo [id=" + id + ", uuidToken=" + uuidToken + "]";
	}

}
