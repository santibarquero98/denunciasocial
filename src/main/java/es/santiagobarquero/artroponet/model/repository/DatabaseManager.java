package es.santiagobarquero.artroponet.model.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DatabaseManager {

	private EntityManager entityManager;
	
	public DatabaseManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("rawtypes")
	public List executeSQLSentence(String sentence) {
		return entityManager.createNativeQuery(sentence).getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List callProcedure(String sentence, HashMap<String, String> params) {
		Query query = entityManager.createNativeQuery(sentence);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

}
