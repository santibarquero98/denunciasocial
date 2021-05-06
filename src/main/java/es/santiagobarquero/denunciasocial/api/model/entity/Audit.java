package es.santiagobarquero.denunciasocial.api.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;

/**
 * Audit entity persist info about the communication of the app
 * 
 * @author santi
 * Audit.java
 */

@Entity
@Table(name = "TB_AUDIT_AUDIT")
public class Audit implements IEntity<Object, Audit> {
	
	public Audit() {
		// empty constructor
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "HOST", length = 15, nullable = false)
	private String host;
	
	@Column(name = "REQUEST", length = 500, nullable = true)
	private String request;
	
	@Column(name = "RESPONSE", length = 500, nullable = true)
	private String response;
	
	@Column(name = "URL_SERVICE", length = 100, nullable = false)
	private String urlService;
	
	@Column(name = "STATUS", length = 2, nullable = false)
	private String status;
	
	@Column(name = "COMMUNICATION_TYPE", length = 3, nullable = false)
	private String communicationType;
	
	@Column(name = "DAT_UP", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datUp;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getUrlService() {
		return urlService;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDatUp() {
		return datUp;
	}

	public void setDatUp(Date datUp) {
		this.datUp = datUp;
	}

	@Override
	public Object getObjectView(boolean lazy) {
		return new Object();
	}

	@Override
	public Audit createNew() {
		return new Audit();
	}

}
