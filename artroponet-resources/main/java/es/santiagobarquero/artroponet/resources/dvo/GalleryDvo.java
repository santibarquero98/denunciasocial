package es.santiagobarquero.artroponet.resources.dvo;

import java.util.List;

public class GalleryDvo {
	
	private Long idGallery;
	
	private String galleryName;
	
	private UserDvo user;
	
	private List<PictureDvo> imagesDvo;
	
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

	public UserDvo getUserDvo() {
		return user;
	}

	public void setUserDvo(UserDvo user) {
		this.user = user;
	}

	public List<PictureDvo> getImagesDvo() {
		return imagesDvo;
	}

	public void setImagesDvo(List<PictureDvo> images) {
		this.imagesDvo = images;
	}
	
	public GalleryDvo createNew() {
		// TODO Auto-generated method stub
		return null;
	}


	public static GalleryDvo createNewInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
