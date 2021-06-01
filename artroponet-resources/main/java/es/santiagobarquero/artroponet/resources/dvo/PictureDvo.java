package es.santiagobarquero.artroponet.resources.dvo;

import java.sql.Blob;

public class PictureDvo {


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
	
	
	public static PictureDvo createNewInstance() {
		return new PictureDvo();
	}

//	@Override
//	public Picture getEntityObject(boolean lazy) {
//		Picture picture = new Picture();
//		picture.setIdPicture(getIdPicture());
//		picture.setImage(getImage());
//		return picture;
//	}

}
