package es.santiagobarquero.denunciasocial.api.controller.complaint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.arch.structureproject.applayer.ProjectRESTemplate;
import es.santiagobarquero.denunciasocial.api.dvo.ComplaintDvo;
import es.santiagobarquero.denunciasocial.api.service.ComplaintService;

@RestController
@RequestMapping("/rest/complaint")
public class ComplaintController implements ProjectRESTemplate<ComplaintDvo>{

	
	@Autowired
	private ComplaintService complaintSrv;
	
	
	@Override
	public ResponseEntity<List<ComplaintDvo>> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpStatus add(ComplaintDvo reqDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpStatus remove(ComplaintDvo reqDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ComplaintDvo> update(ComplaintDvo reqDvo) {
		// TODO Auto-generated method stub
		return null;
	}

}
