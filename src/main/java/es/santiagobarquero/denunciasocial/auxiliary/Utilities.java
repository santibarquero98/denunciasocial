package es.santiagobarquero.denunciasocial.auxiliary;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Utilities {

	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || str.isEmpty() ? true : false;
	}
	
	public static String encryptPassword(String password) {
		if(isNullOrEmpty(password)) {
			return DenunciasocialConstants.EMPTY_CHAIN;
		}
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean isNullOrEmpty(List<?> list) {
		return isNull(list) || list.isEmpty() ? true : false;
	}
	
}
