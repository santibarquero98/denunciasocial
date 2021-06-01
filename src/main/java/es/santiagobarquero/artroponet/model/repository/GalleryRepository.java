package es.santiagobarquero.artroponet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.santiagobarquero.artroponet.model.entity.Gallery;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

}
