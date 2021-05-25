package es.santiagobarquero.artroponet.resources.dvo;

import java.text.ParseException;

import org.slf4j.Logger;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.LogAction;
import es.santiagobarquero.denunciasocial.api.model.entity.Audit;

public class AuditMsg implements IDvo<Audit, AuditMsg> {

	private Long id;
	
	private String host;
	
	private String request;
	
	private String response;
	
	private String urlService;
	
	private String status;
	
	private String communicationType;
	
	private String datUp;
	
	public AuditMsg() {
		super();
	}
	
	public AuditMsg(Long id, String host, String request, String response, String urlService, String status, String communicationType, String datUp) {
		super();
		this.id = id;
		this.host = host;
		this.request = request;
		this.response = response;
		this.urlService = urlService;
		this.status = status;
		this.communicationType = communicationType;
		this.datUp = datUp;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getDatUp() {
		return datUp;
	}

	public void setDatUp(String datUp) {
		this.datUp = datUp;
	}

	@Override
	public AuditMsg createNew() {
		return new AuditMsg();
	}

	@Override
	public Audit getEntityObject(boolean lazy) {
		Audit a = new Audit();
		a.setCommunicationType(getCommunicationType());
		a.setHost(getHost());
		a.setId(getId());
		a.setRequest(getRequest());
		a.setResponse(getResponse());
		a.setStatus(getStatus());
		a.setUrlService(getUrlService());
		try {
			String datUp = getDatUp();
			if(!Utilities.isNullOrBlank(datUp)) {
				a.setDatUp(Utilities.stringToDate(datUp, ArtroponetConstants.STANDARD_PROJECT_DATE));
			}
		} catch (ParseException e) {
			Logger logger = LogAction.getLogger(AuditMsg.class);
			logger.info(String.format("Error to convert stringToDate: -> %s", e.getLocalizedMessage()), e);
			logger = null;
		}
		return a;
	}

}
