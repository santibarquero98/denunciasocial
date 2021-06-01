package es.santiagobarquero.artroponet.resources.dvo;

import java.util.List;

public class GenderDvo {

	private Long idGender;
	
	private String nameGender;
	
    private List<TarantulaDvo> tarantulasDvo;
    
	
	public GenderDvo() {
		super();
	}
    
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
	
	public List<TarantulaDvo> getTarantulasDvo() {
		return tarantulasDvo;
	}
	
	public void setTarantulasDvo(List<TarantulaDvo> tarantulasDvo) {
		this.tarantulasDvo = tarantulasDvo;
	}
	
	public static GenderDvo createNewInstance() {
		return new GenderDvo();
	}
	
//	@Override
//	public Gender getEntityObject(boolean lazy) {
//		Gender gender = new Gender();
//		gender.setIdGender(getIdGender());
//		gender.setNameGender(getNameGender());
//		if(lazy) {
//			List<Tarantula> tarantulas = new ArrayList<>(ArtroponetConstants.ZERO);
//			for(TarantulaDvo tDvo : getTarantulasDvo()) {
//				tarantulas.add(tDvo.getEntityObject(lazy));
//			}
//			gender.setTarantulas(tarantulas);
//		}
//		return gender;
//	}
    
}
