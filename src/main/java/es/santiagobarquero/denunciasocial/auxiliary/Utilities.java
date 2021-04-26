package es.santiagobarquero.denunciasocial.auxiliary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Description: Utilities class is a support class for simple actions in Java
 * @author santi
 * Utilities.java
 */
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Utilities {

	private Utilities() {
		// private constructor
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty() ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
	}

	public static String encryptPassword(String password) {
		if (isNullOrEmpty(password)) {
			return ArtroponetConstants.EMPTY_CHAIN;
		}
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty() ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
	}

	// yyyy-MM-dd HH:mm:ss.SSS
	public static Date dateToString(String datUpStr, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(datUpStr);
	}

	// yyyy-MM-dd HH:mm:ss.SSS
	public static String stringToDate(Date datUp, String format) throws ParseException {
		return new SimpleDateFormat(format).format(datUp);
	}

}
