package es.santiagobarquero.artroponet.resources.dvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDvo {

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

//	@Override
//	public Token getEntityObject(boolean lazy) {
//		Token t = new Token();
//		t.setId(getId());
//		t.setUuidToken(getUuidToken());
//		if(lazy) {
//			t.setUser(getUserDvo().getEntityObject(false));
//		}
//		return t;
//	}

	
	public static TokenDvo createNewInstance() {
		return new TokenDvo();
	}

	@Override
	public String toString() {
		return "TokenDvo [id=" + id + ", uuidToken=" + uuidToken + "]";
	}

}
