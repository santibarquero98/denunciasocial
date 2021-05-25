package es.santiagobarquero.artroponet.resources.dvo;

import java.sql.Blob;

import es.santiagobarquero.arch.structureproject.applayer.IDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Picture;

public class PictureDvo implements IDvo<Picture, PictureDvo> {


	private Long idPicture;
	private Blob image;
	
	public PictureDvo() {
		super();
	}

	public Long getIdPicture() {
		return idPicture;
	}

	public void setIdPicture(Long idPicture) {
		this.idPicture = idPicture;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
	
	@Override
	public PictureDvo createNew() {
		return new PictureDvo();
	}

	@Override
	public Picture getEntityObject(boolean lazy) {
		Picture picture = new Picture();
		picture.setIdPicture(getIdPicture());
		picture.setImage(getImage());
		return picture;
	}

}
