package es.santiagobarquero.denunciasocial.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.santiagobarquero.denunciasocial.api.model.entity.Tarantula;

@Repository
public interface TarantulaRepository extends JpaRepository<Tarantula, Long> {

}
