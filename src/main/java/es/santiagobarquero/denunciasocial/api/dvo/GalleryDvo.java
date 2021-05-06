package es.santiagobarquero.denunciasocial.api.dvo;

import java.util.List;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Gallery;
import es.santiagobarquero.denunciasocial.api.model.entity.Picture;
import es.santiagobarquero.denunciasocial.api.model.entity.User;

public class GalleryDvo implements IDvo<Gallery, GalleryDvo> {
	
	private Long idGallery;
	
	private String galleryName;
	
	private User user;
	
	private List<Picture> images;
	
	public GalleryDvo() {
		super();
	}
	

	public Long getIdGallery() {
		return idGallery;
	}

	public void setIdGallery(Long idGallery) {
		this.idGallery = idGallery;
	}

	public String getGalleryName() {
		return galleryName;
	}

	public void setGalleryName(String galleryName) {
		this.galleryName = galleryName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Picture> getImages() {
		return images;
	}

	public void setImages(List<Picture> images) {
		this.images = images;
	}
	
	@Override
	public GalleryDvo createNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gallery getEntityObject(boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}

}
