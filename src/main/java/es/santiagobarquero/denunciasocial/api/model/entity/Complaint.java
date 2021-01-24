package es.santiagobarquero.denunciasocial.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.ComplaintDvo;

/**
 * Complaint entity persist info about the details of a complaint
 * 
 * @author santi
 * Complaint.java
 */

@Entity
@Table(name = "complaint")
public class Complaint implements IEntity<ComplaintDvo, Complaint> {

	public Complaint() {
		// empty constructor
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", length = 15)
	private String title;
	
	@Column(name = "manualDescription", length = 15)
	private String manualDescription;
	
	@Column(name = "destinationName", length = 15)
	private String destinationName;
	
	@Column(name = "complaintType", length = 15)
	private String complaintTypeString;
	
	
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
	public ComplaintDvo getObjectView(boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complaint createNew() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
