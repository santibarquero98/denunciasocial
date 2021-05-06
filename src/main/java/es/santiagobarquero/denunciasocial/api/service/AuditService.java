package es.santiagobarquero.denunciasocial.api.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.controller.auth.UserController;
import es.santiagobarquero.denunciasocial.api.dvo.AuditMsg;
import es.santiagobarquero.denunciasocial.api.model.entity.Audit;
import es.santiagobarquero.denunciasocial.api.model.repository.AuditRepository;
import es.santiagobarquero.denunciasocial.auxiliary.ArtroponetConstants;
import es.santiagobarquero.denunciasocial.auxiliary.LogAction;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.NotImplementedException;

@Service
public class AuditService implements ServiceInterface<Audit, Audit> {

	private Logger logger;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Autowired
	private AuditRepository auditRepository;

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@PostConstruct
	public void init() {
		logger = LogAction.getLogger(UserController.class);
	}
	
	/*
	 * METHODS
	 */
	
	@Transactional
	public void auditGetRequest(String hostname, String url, boolean ok) throws NotImplementedException {
		if(ok) {
			AuditMsg auditMsg = new AuditMsg();
			auditMsg.setCommunicationType("IN");
			auditMsg.setHost(hostname);
			auditMsg.setResponse("OK");
			auditMsg.setStatus("OK");
			auditMsg.setUrlService(url);
			try {
				auditMsg.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
			} catch (ParseException e) {
				logger.info(String.format("Error to convert dateToString: -> %s", e.getLocalizedMessage()), e);
			}
			create(auditMsg.getEntityObject(false), false, false);
		} else {
			throw new NotImplementedException();
		}
	
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
	public void delete(Audit audit, boolean flushOnFinish) {
		auditRepository.delete(audit);
		
	}

	@Override
	public List<Audit> getAllsDvo(boolean lazy) {
		return auditRepository.findAll();
	}

	@Override
	public List<Audit> getAllsEntity(boolean lazy) {
		return getAllsDvo(lazy);
	}

	@Override
	public Audit update(Audit audit, boolean flushOnFinish) {
		Audit auditSaved = auditRepository.save(audit);
		if(flushOnFinish) auditRepository.flush();
		return auditSaved;
	}

}
