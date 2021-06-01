package es.santiagobarquero.artroponet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.artroponet.model.repository.TarantulaRepository;

@Service("tarantulaService")
public class TarantulaService {

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private TarantulaRepository tarantulaRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	
	
}
