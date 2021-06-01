package es.santiagobarquero.artroponet.service;

import java.util.List;

import es.santiagobarquero.artroponet.auxiliary.exceptions.NotImplementedException;
import es.santiagobarquero.artroponet.model.entity.Audit;

public interface IAuditService {
	
	public void auditGetRequest(String hostname, String url, boolean ok) throws NotImplementedException;
	
	
	public void remAudit(Audit audit);

	
	public Audit create(Audit audit, boolean lazy, boolean flushOnFinish);

	
	public void delete(Audit audit, boolean flushOnFinish);

	
	public List<Audit> getAllsDvo(boolean lazy);

	
	public List<Audit> getAllsEntity(boolean lazy);

	
	public Audit update(Audit audit, boolean flushOnFinish);
}
