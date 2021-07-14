package es.santiagobarquero.artroponet.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import es.santiagobarquero.artroponet.resources.enums.CommunicationType;

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
	
	/**
	 * @param String hostname: host of the caller
	 * @param String responseBody: the body response
	 * @param HttpStatus httpStatus: result for the call
	 * @param String error message: error message
	 * @param boolean ok: sucessfull or program error
	 * @throws NotImplementedException
	 */
	@Override
	@Transactional
	public void auditGetRequest(String hostname, String url, String responseBody, HttpStatus httpStatus, String errorMessage, boolean ok) throws NotImplementedException {
		AuditMsg auditMsg = getSimpleAuditMsg(hostname, url);
		auditMsg.setResponse(responseBody);
		auditMsg.setStatus(httpStatus.toString());
		if(ok) {
			create(auditMsg);
		} else {
			auditMsg.setDescription(errorMessage);
			create(auditMsg);
		}
	}
	

	@Override
	@Transactional
	public void auditPostRequest(String hostname, String url, String reqBody, String responseBody, HttpStatus httpStatus, String errorMessage, boolean ok) throws NotImplementedException {
		AuditMsg auditMsg = getSimpleAuditMsg(hostname, url);
		auditMsg.setRequest(reqBody);
		auditMsg.setStatus(httpStatus.toString());
		auditMsg.setResponse(responseBody);
		if(ok) {
			create(auditMsg);
		} else {
			auditMsg.setDescription(errorMessage);
			create(auditMsg);
		}
	
	}
	
	@Transactional
	@Override
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
	

	
	private void create(AuditMsg auditMsg) {
		create(AuditConverter.getObjectEntity(auditMsg, false), false, false);
	}
	
	private AuditMsg getSimpleAuditMsg(String hostname, String url) {
		AuditMsg auditMsg = new AuditMsg();
		auditMsg.setCommunicationType(CommunicationType.REST);
		auditMsg.setHost(hostname);
		auditMsg.setUrlService(url);
		try {
			auditMsg.setDatUp(Utilities.dateToString(new Date(), ArtroponetConstants.STANDARD_PROJECT_DATE));
		} catch (ParseException e) {
			logger.info(String.format("Error to convert dateToString: -> %s", e.getLocalizedMessage()), e);
		}
		return auditMsg;
	}

}
