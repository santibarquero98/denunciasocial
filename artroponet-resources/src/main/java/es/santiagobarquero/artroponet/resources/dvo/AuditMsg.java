package es.santiagobarquero.artroponet.resources.dvo;

import es.santiagobarquero.artroponet.resources.enums.CommunicationType;

public class AuditMsg {

	private Long id;
	
	private String host;
	
	private String request;
	
	private String response;
	
	private String urlService;
	
	private String status;
	
	private CommunicationType communicationType;
	
	private String datUp;
	
	private String description;
	
	public AuditMsg() {
		super();
	}
	
	public AuditMsg(Long id, String host, String request, String response, String urlService, String status, CommunicationType communicationType, String datUp, String description) {
		super();
		this.id = id;
		this.host = host;
		this.request = request;
		this.response = response;
		this.urlService = urlService;
		this.status = status;
		this.communicationType = communicationType;
		this.datUp = datUp;
		this.description = description;
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

	public CommunicationType getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(CommunicationType communicationType) {
		this.communicationType = communicationType;
	}

	public String getDatUp() {
		return datUp;
	}

	public void setDatUp(String datUp) {
		this.datUp = datUp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static AuditMsg createNewInstance() {
		return new AuditMsg();
	}
	

}
