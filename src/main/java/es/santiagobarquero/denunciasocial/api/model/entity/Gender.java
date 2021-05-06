package es.santiagobarquero.denunciasocial.api.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.GenderDvo;
import es.santiagobarquero.denunciasocial.api.dvo.TarantulaDvo;
import es.santiagobarquero.denunciasocial.auxiliary.ArtroponetConstants;

@Entity
@Table(name = "tb_genders")
public class Gender implements IEntity<GenderDvo, Gender>{
	
	public Gender() {
		// empty constructor
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_GENDER")
	private Long idGender;
	
	@Column(name = "NOM_GENDER")
	private String nameGender;
	
	@OneToMany(mappedBy = "idTarantula", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
    private List<Tarantula> tarantulas;
	
	public Long getIdGender() {
		return idGender;
	}

	public void setIdGender(Long idGender) {
		this.idGender = idGender;
	}

	public String getNameGender() {
		return nameGender;
	}

	public void setNameGender(String nameGender) {
		this.nameGender = nameGender;
	}

	public List<Tarantula> getTarantulas() {
		return tarantulas;
	}

	public void setTarantulas(List<Tarantula> tarantulas) {
		this.tarantulas = tarantulas;
	}

	@Override
	public Gender createNew() {
		return new Gender();
	}

	@Override
	public GenderDvo getObjectView(boolean lazy) {
		GenderDvo genderDvo = new GenderDvo();
		genderDvo.setIdGender(getIdGender());
		genderDvo.setNameGender(getNameGender());
		if(lazy) {
			List<TarantulaDvo> tarantulasDvo = new ArrayList<>(ArtroponetConstants.ZERO);
			for(Tarantula t : getTarantulas()) {
				tarantulasDvo.add(t.getObjectView(lazy));
			}
			genderDvo.setTarantulasDvo(tarantulasDvo);
		}
		return genderDvo;
	}

}
