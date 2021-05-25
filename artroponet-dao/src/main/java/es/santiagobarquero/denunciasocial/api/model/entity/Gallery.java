package es.santiagobarquero.denunciasocial.api.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.GalleryDvo;

@Entity
@Table(name = "tb_gallery")
public class Gallery implements IEntity<GalleryDvo, Gallery> {
	
	public Gallery() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gallery")
	private Long idGallery;
	
	@Column(name = "GALLERY_NAME", length = 500, nullable = true)
	private String galleryName;
	
	@ManyToOne()
    @JoinColumn(name = "ID_USER")
	private User user;
	
	@OneToMany(mappedBy = "idPicture", cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	private List<Picture> images;

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
	public Gallery createNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GalleryDvo getObjectView(boolean lazy) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
