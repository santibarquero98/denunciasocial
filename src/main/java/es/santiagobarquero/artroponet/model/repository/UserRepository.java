package es.santiagobarquero.artroponet.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.santiagobarquero.artroponet.model.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	@Query("select u from User u where username = ?1")
	User getUserByUsername(String username);
	
	
}
