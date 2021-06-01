package es.santiagobarquero.artroponet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santiagobarquero.artroponet.dvo.converters.PictureConverter;
import es.santiagobarquero.artroponet.model.repository.PictureRepository;
import es.santiagobarquero.artroponet.resources.dvo.PictureDvo;

@Service("pictureService")
public class PictureServiceImpl implements IPictureService {
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Autowired
	private PictureRepository galleryRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //
	
	@Override
	public void create(PictureDvo pictureDvo) {
		galleryRepository.save(PictureConverter.getObjectEntity(pictureDvo, true));
	}

}
