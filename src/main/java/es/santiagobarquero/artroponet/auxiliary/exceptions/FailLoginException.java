package es.santiagobarquero.artroponet.auxiliary.exceptions;import org.springframework.http.HttpStatus;

public class FailLoginException extends Exception {
	
	private static final long serialVersionUID = -3743613289974580973L;
	
	private HttpStatus httpStatus;
	
	public FailLoginException() {
		super();
	}
	
	public FailLoginException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
