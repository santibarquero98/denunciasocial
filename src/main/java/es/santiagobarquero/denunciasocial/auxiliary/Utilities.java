package es.santiagobarquero.denunciasocial.auxiliary;

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
		if(isNullOrEmpty(password)) {
			return DenunciasocialConstants.EMPTY_CHAIN;
		}
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty() ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
	}
	
}
