package es.santiagobarquero.artroponet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.santiagobarquero.artroponet.model.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
