package es.santiagobarquero.artroponet.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import es.santiagobarquero.artroponet.auxiliary.exceptions.NotImplementedException;
import es.santiagobarquero.artroponet.model.entity.Audit;

public interface IAuditService {
	
	public void remAudit(Audit audit);
	
	public Audit create(Audit audit, boolean lazy, boolean flushOnFinish);
	
	public void delete(Audit audit, boolean flushOnFinish);
	
	public List<Audit> getAllsDvo(boolean lazy);
	
	public List<Audit> getAllsEntity(boolean lazy);

	public Audit update(Audit audit, boolean flushOnFinish);

	void auditGetRequest(String hostname, String url, String responseBody, HttpStatus httpStatus, String errorMessage, boolean ok) throws NotImplementedException;

	void auditPostRequest(String hostname, String url, String reqBody, String responseBody, HttpStatus httpStatus, String errorMessage, boolean ok) throws NotImplementedException;
}
