package es.santiagobarquero.artroponet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.santiagobarquero.artroponet.model.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query("select t from Token t where uuid_token = ?1")
	Token getTokenByUUID(String uuid);
	
}
