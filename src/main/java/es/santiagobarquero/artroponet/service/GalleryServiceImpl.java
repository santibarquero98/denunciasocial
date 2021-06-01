package es.santiagobarquero.artroponet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.artroponet.model.repository.GalleryRepository;

@Service("galleryService")
public class GalleryServiceImpl implements IGalleryService {

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
}
