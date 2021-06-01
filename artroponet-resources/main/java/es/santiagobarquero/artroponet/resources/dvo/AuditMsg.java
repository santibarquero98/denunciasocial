package es.santiagobarquero.artroponet.resources.dvo;

public class AuditMsg {

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

	public static AuditMsg createNewInstance() {
		return new AuditMsg();
	}
	

}
