package es.santiagobarquero.artroponet.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tarantulas")
public class Tarantula { 
//implements IEntity<TarantulaDvo, Tarantula> {
	
	public Tarantula() {
		// empty constructor
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TARANTULA")
	private Long idTarantula;
	
	@Column(name = "NOM_COMUN")
	private String comunName;
	
	@Column(name = "NOM_ESPECIE")
	private String specieName;
	
	@ManyToOne()
    @JoinColumn(name = "ID_GENDER")
	private Gender gender;
	
	@ManyToOne()
    @JoinColumn(name = "ID_USER")
	private User user;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getSpecieName() {
		return specieName;
	}

	public void setSpecieName(String specieName) {
		this.specieName = specieName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

//	@Override
//	public Tarantula createNew() {
//		return new Tarantula();
//	}
//
//	@Override
//	public TarantulaDvo getObjectView(boolean lazy) {
//		TarantulaDvo tarantulaDvo = new TarantulaDvo();
//		return tarantulaDvo;
//	}
	
}
