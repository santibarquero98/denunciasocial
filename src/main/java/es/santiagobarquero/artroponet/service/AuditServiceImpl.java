package es.santiagobarquero.artroponet.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.artroponet.auxiliary.ArtroponetConstants;
import es.santiagobarquero.artroponet.auxiliary.LogAction;
import es.santiagobarquero.artroponet.auxiliary.Utilities;
import es.santiagobarquero.artroponet.auxiliary.exceptions.NotImplementedException;
import es.santiagobarquero.artroponet.controller.UserController;
import es.santiagobarquero.artroponet.dvo.converters.AuditConverter;
import es.santiagobarquero.artroponet.model.entity.Audit;
import es.santiagobarquero.artroponet.model.repository.AuditRepository;
import es.santiagobarquero.artroponet.resources.dvo.AuditMsg;

@Service
public class AuditServiceImpl implements IAuditService {

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
			create(AuditConverter.getObjectEntity(auditMsg, false), false, false);
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
