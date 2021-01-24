package es.santiagobarquero.denunciasocial.auxiliary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogAction {
	
	public static void debug(Class<?> clazz, String msg, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.debug(msg);
	}
	
	public static void info(Class<?> clazz, String msg, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.info(msg);
	}
	
	public static void error(Class<?> clazz, String msg, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.error(msg);
	}
	
	public static void warn(Class<?> clazz, String msg, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		logger.warn(msg);
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
	

}
