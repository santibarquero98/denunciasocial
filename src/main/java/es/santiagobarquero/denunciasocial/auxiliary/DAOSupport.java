package es.santiagobarquero.denunciasocial.auxiliary;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
public class DAOSupport<T> {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unchecked")
	public List<T> executeQuery(String name) {
		List<T> avisos;
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNamedQuery(name);
		avisos = q.getResultList();
		return avisos;
	}
}
