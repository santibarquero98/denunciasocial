package es.santiagobarquero.denunciasocial.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.dvo.ComplaintDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Complaint;
import es.santiagobarquero.denunciasocial.api.model.repository.ComplaintRepository;
import es.santiagobarquero.denunciasocial.auxiliary.DenunciasocialConstants;

@Service
public class ComplaintService implements ServiceInterface<ComplaintDvo, Complaint> {

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Autowired
	private ComplaintRepository complaintRepository;

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Override
	public ComplaintDvo create(ComplaintDvo complaintDvo, boolean lazy, boolean flushOnFinish) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ComplaintDvo complaintDvo, boolean flushOnFinish) {
		complaintRepository.delete(complaintDvo.getEntityObject(false));
	}

	@Override
	public List<ComplaintDvo> getAllsDvo(boolean lazy) {
		List<Complaint> resultDb = complaintRepository.findAll();
		List<ComplaintDvo> resultDvo = new ArrayList<>(DenunciasocialConstants.ZERO);
		for(Complaint c : resultDb) {
			resultDvo.add(c.getObjectView(lazy));
		}
		return resultDvo;
	}

	@Override
	public List<Complaint> getAllsEntity(boolean lazy) {
		return complaintRepository.findAll();
	}

	@Override
	public ComplaintDvo update(ComplaintDvo complaintDvo, boolean flushOnFinish) {
		return complaintRepository.save(complaintDvo.getEntityObject(false)).getObjectView(false);
	}

}
