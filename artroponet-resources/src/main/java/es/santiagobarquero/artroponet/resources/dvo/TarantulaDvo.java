package es.santiagobarquero.artroponet.resources.dvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Tarantula;

public class TarantulaDvo implements IDvo<Tarantula, TarantulaDvo> {
	
	public TarantulaDvo() {
		// empty constructor
	}
	
	@JsonIgnore
	private Long idTarantula;
	
	@JsonProperty("comun_name")
	private String comunName;
	
	@JsonProperty("gender")
	private GenderDvo genderDvo;
	
	@JsonProperty("specie_name")
	private String specieName;
	
	@JsonProperty("user")
	private UserDvo userDvo;
	
	public Long getIdTarantula() {
		return idTarantula;
	}
	
	public void setIdTarantula(Long idTarantula) {
		this.idTarantula = idTarantula;
	}
	
	public String getComunName() {
		return comunName;
	}
	
	public void setComunName(String comunName) {
		this.comunName = comunName;
	}
	
	public GenderDvo getGenderDvo() {
		return genderDvo;
	}
	
	public void setGenderDvo(GenderDvo genderDvo) {
		this.genderDvo = genderDvo;
	}
	
	public String getSpecieName() {
		return specieName;
	}
	
	public void setSpecieName(String specieName) {
		this.specieName = specieName;
	}
	
	public UserDvo getUserDvo() {
		return userDvo;
	}
	
	public void setUserDvo(UserDvo userDvo) {
		this.userDvo = userDvo;
	}
	
	
	@Override
	public TarantulaDvo createNew() {
		return new TarantulaDvo();
	}
	
	@Override
	public Tarantula getEntityObject(boolean lazy) {
		Tarantula tarantula = new Tarantula();
		tarantula.setComunName(getComunName());
		if(lazy)  {
			tarantula.setGender(getGenderDvo().getEntityObject(lazy));
			tarantula.setUser(getUserDvo().getEntityObject(lazy));
		}
		tarantula.setIdTarantula(getIdTarantula());
		tarantula.setSpecieName(getSpecieName());
		return tarantula;
	}

}
