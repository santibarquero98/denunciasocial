package es.santiagobarquero.artroponet.dvo.converters;

import es.santiagobarquero.artroponet.model.entity.Audit;
import es.santiagobarquero.artroponet.resources.dvo.AuditMsg;

public final class AuditConverter {

	private AuditConverter() {
		super();
	}
	
	public static AuditMsg getObjectView(Audit audit, boolean lazy) {
		return null;
	}
	
	public static Audit getObjectEntity(AuditMsg auditMsg, boolean lazy) {
		return null;
	}
	
}
