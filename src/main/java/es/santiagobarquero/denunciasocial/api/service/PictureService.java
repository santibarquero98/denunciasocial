package es.santiagobarquero.denunciasocial.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.denunciasocial.api.dvo.PictureDvo;
import es.santiagobarquero.denunciasocial.api.model.repository.PictureRepository;

@Service
public class PictureService {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private PictureRepository galleryRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	public void create(PictureDvo pictureDvo) {
		galleryRepository.save(pictureDvo.getEntityObject(false));
	}

}
