package es.santiagobarquero.denunciasocial.api.model.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.PictureDvo;

@Entity
@Table(name = "tb_pictures")
public class Picture implements IEntity<PictureDvo, Picture> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PICTURE")
	private Long idPicture;
	
	@Lob
	@Column(name = "IMAGE", columnDefinition="LONGBLOB")
	private Blob image;
	
	@ManyToOne()
    @JoinColumn(name = "ID_GALERY")
	private Gallery gallery;
	
	public Picture() {
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
	public Picture createNew() {
		return new Picture();
	}

	@Override
	public PictureDvo getObjectView(boolean lazy) {
		PictureDvo pictureDvo = new PictureDvo();
		pictureDvo.setIdPicture(getIdPicture());
		pictureDvo.setImage(getImage());
		return pictureDvo;
	}
	
	
	
}
