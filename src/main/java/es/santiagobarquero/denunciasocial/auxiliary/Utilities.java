package es.santiagobarquero.denunciasocial.auxiliary;
/**
 * Description: Utilities class is a support class for simple actions in Java
 * @author santi
 * Utilities.java
 */
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.crypto.bcrypt.BCrypt;

import es.santiagobarquero.denunciasocial.api.service.TokenService;

public class Utilities {
	
	private Utilities() {
		// private constructor
	}
	
	public static boolean requestIsAuth(Map<String, String> headers, TokenService tokenService) {
		String[] auth = Utilities.findCredentialsInHeader(headers);
		return tokenService.checkTokenUserRelation(auth[1], auth[0]);
	}
	
	public static String[] findCredentialsInHeader(Map<String, String> headers) {
		String[] result = new String[2];
		for(Entry<String, String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(AppHeaders.HUSERNAME_HEADER.equals(key)) {
				result[0] = value;
				continue;
			}
			if(AppHeaders.HTOKEN_HEADER.equals(key)) {
				result[1] = value;
				break;
			}
		}
		return result;
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
