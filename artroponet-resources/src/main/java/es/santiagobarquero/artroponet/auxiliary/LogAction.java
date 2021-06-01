package es.santiagobarquero.artroponet.auxiliary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogAction {
	
	private LogAction() {
		// empty constructor
	}
	
	public static void debug(Class<?> clazz, String msg, Exception e) {
		Logger logger = LoggerFactory.getLogger(clazz);
		String msgException = ArtroponetConstants.EMPTY_CHAIN;
		if(e != null) {
			msgException = e.getMessage();
		}
		logger.debug(formatMessage(msg, msgException));
	}
	
	public static void info(Class<?> clazz, String msg, Exception e) {
		Logger logger = getLogger(clazz);
		String msgException = ArtroponetConstants.EMPTY_CHAIN;
		if(e != null) {
			msgException = e.getMessage();
		}
		logger.info(formatMessage(msg, msgException));
	}
	
	public static void error(Class<?> clazz, String msg, Exception e) {
		Logger logger = getLogger(clazz);
		String msgException = ArtroponetConstants.EMPTY_CHAIN;
		if(e != null) {
			msgException = e.getMessage();
		}
		logger.error(formatMessage(msg, msgException));
	}
	
	public static void warn(Class<?> clazz, String msg, Exception e) {
		Logger logger = getLogger(clazz);
		String msgException = ArtroponetConstants.EMPTY_CHAIN;
		if(e != null) {
			msgException = e.getMessage();
		}
		logger.warn(formatMessage(msg, msgException));
	}
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
	
	private static String formatMessage(String msg, String msgException) {
		return String.format("-- %s -- || Exception message is -> %s", msg, msgException);
	}
	

}
