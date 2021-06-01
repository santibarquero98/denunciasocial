package es.santiagobarquero.artroponet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.artroponet.controller.IProjectRESTemplate;
import es.santiagobarquero.artroponet.resources.dvo.TarantulaDvo;
import es.santiagobarquero.artroponet.service.TarantulaService;

@RestController
@RequestMapping("/rest/tarantula")
public class TarantulaController implements IProjectRESTemplate<TarantulaDvo> {

	@Autowired
	private TarantulaService tarantulaService;
	
	@Override
	public HttpStatus add(TarantulaDvo tarantulaDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<TarantulaDvo>> getAlls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpStatus remove(TarantulaDvo tarantulaDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<TarantulaDvo> update(TarantulaDvo tarantulaDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
