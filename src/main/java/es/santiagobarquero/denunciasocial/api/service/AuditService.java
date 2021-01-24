package es.santiagobarquero.denunciasocial.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.denunciasocial.api.model.entity.Audit;
import es.santiagobarquero.denunciasocial.api.model.repository.AuditRepository;

@Service
public class AuditService {

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Autowired
	private AuditRepository auditRepository;

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	
	/*
	 * METHODS
	 */
	
	@Transactional
	public void audit(String host, String request, String response, String urlService, String status, String communicationType) {
		Audit audit = new Audit();
		audit.setHost(host);
		audit.setRequest(request);
		audit.setResponse(response);
		audit.setUrlService(urlService);
		audit.setStatus(status);
		audit.setCommunicationType(communicationType);
		auditRepository.save(audit);
	}
	
	public void remAudit(Audit audit) {
		auditRepository.delete(audit);
	}

}
