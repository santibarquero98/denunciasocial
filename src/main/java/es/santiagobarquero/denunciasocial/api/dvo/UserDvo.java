package es.santiagobarquero.denunciasocial.api.dvo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Payroll;
import es.santiagobarquero.denunciasocial.api.model.entity.User;
import es.santiagobarquero.denunciasocial.auxiliary.DenunciasocialConstants;

/**
 * UserDvo class defines the POJO from User entity (User Data View Object)
 * 
 * @author santi
 *
 */
@JsonRootName("userDvo")
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
	
	@JsonProperty("payrolls")
	private List<PayrollDvo> payrollsDvo;

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

	public List<PayrollDvo> getPayrollsDvo() {
		return payrollsDvo;
	}

	public void setPayrollsDvo(List<PayrollDvo> payrollsDvo) {
		this.payrollsDvo = payrollsDvo;
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
			
			TokenDvo tokenDvo = getTokenDvo();
			if(tokenDvo != null) {
				user.setToken(tokenDvo.getEntityObject(false));
			}
			
			List<PayrollDvo> payrollsDvo = getPayrollsDvo();
			if(payrollsDvo != null) {
				List<Payroll> payrolls = new ArrayList<>(DenunciasocialConstants.ZERO);
				for(PayrollDvo p : this.payrollsDvo) {
					payrolls.add(p.getEntityObject(false));
				}
				user.setPayrolls(payrolls);
			}
		}
		return user;
	}

	@Override
	public UserDvo createNew() {
		return new UserDvo();
	}


	@Override
	public String toString() {
		String tokenDvoString = tokenDvo != null ? tokenDvo.toString() : null;
		return "UserDvo [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", active=" + active + ", tokenDvo=" + tokenDvoString + "]";
	}
	

}
