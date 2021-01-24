package es.santiagobarquero.denunciasocial.api.dvo;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Complaint;
/**
 * ComplaintDvo class defines the POJO from Complaint entity (Complaint Data View Object)
 * 
 * @author santi
 *
 */
public class ComplaintDvo implements IDvo<Complaint, ComplaintDvo> {
	

	private Long id;
	
	private String title;
	
	private String manualDescription;
	
	private String destinationName;
	
	private String complaintTypeString;
	
	public ComplaintDvo() {
		// empty constructor
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManualDescription() {
		return manualDescription;
	}

	public void setManualDescription(String manualDescription) {
		this.manualDescription = manualDescription;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getComplaintTypeString() {
		return complaintTypeString;
	}

	public void setComplaintTypeString(String complaintTypeString) {
		this.complaintTypeString = complaintTypeString;
	}

	@Override
	public Complaint getEntityObject(boolean lazy) {
		Complaint c = new Complaint();
		c.setComplaintTypeString(this.getComplaintTypeString());
		c.setDestinationName(this.getDestinationName());
		c.setId(this.getId());
		c.setManualDescription(this.getManualDescription());
		c.setTitle(this.getTitle());
		return c;
	}

	@Override
	public ComplaintDvo createNew() {
		return new ComplaintDvo();
	}

}
