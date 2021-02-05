package es.santiagobarquero.denunciasocial.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.model.entity.Audit;
import es.santiagobarquero.denunciasocial.api.model.repository.AuditRepository;

@Service
public class AuditService implements ServiceInterface<Audit, Audit> {

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
		create(audit, false, false);
	}
	
	@Transactional
	public void remAudit(Audit audit) {
		auditRepository.delete(audit);
	}

	@Override
	public Audit create(Audit audit, boolean lazy, boolean flushOnFinish) {
		return auditRepository.save(audit);
	}

	@Override
	public void delete(Audit arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Audit> getAllsDvo(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Audit> getAllsEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit update(Audit arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
