package es.santiagobarquero.denunciasocial.api.controller.auth;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.arch.structureproject.applayer.ProjectRESTemplate;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.service.AuditService;
import es.santiagobarquero.denunciasocial.api.service.TokenService;
import es.santiagobarquero.denunciasocial.api.service.UserService;
import es.santiagobarquero.denunciasocial.auxiliary.AppHeaders;
import es.santiagobarquero.denunciasocial.auxiliary.LogAction;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;

@RestController
@RequestMapping("/rest/user")
public class UserController implements ProjectRESTemplate<UserDvo> {
	
    Logger logger = null;
	
	@Autowired
	private UserService userSrv;
	
	@Autowired
	private AuditService auditSrv;
	
	@Autowired
	private TokenService tokenSrv;
	
	@PostConstruct
	public void init() {
		logger = LogAction.getLogger(UserController.class);
	}
	
	@RequestMapping("/")
	public HttpStatus throwResponse(@RequestHeader HttpHeaders reqHeader) {
		InetSocketAddress inetSocket = reqHeader.getHost();
		if(inetSocket != null && inetSocket.getAddress() != null) {
			auditSrv.audit(inetSocket.getAddress().getHostAddress(), null, "OK", "/rest/user/", "OK", "IN");
		}
		return HttpStatus.OK;
	}
	
	@PostMapping("/dologin")
	public ResponseEntity<TokenDvo> doLogin(@RequestBody UserDvo reqUserDvo) {
		String username = reqUserDvo.getUsername();
		String password = reqUserDvo.getPassword();
		logger.info(String.format("%s is trying to login --", username));
		UserDvo userDvo = userSrv.findUserByUsername(username);
		if(userDvo == null) {
			logger.info(String.format("-- Login KO for %s --", username));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		boolean okCredentials = BCrypt.checkpw(password, userDvo.getPassword());
		
		if(okCredentials) {
			TokenDvo oldToken = userDvo.getTokenDvo();
			// Generate a new token for the next auths
			TokenDvo newToken = tokenSrv.generate();
			logger.info(String.format("-- Login OK for %s --", username));
			logger.info(String.format("-- The old token is -> %s --", userDvo.getTokenDvo().getUuidToken()));
			logger.info(String.format("-- The new token is -> %s --", newToken.getUuidToken()));
			userDvo.setTokenDvo(newToken);
			userDvo = userSrv.update(userDvo, true);
			logger.info("-- Token updated --");

			// Delete the old token
			tokenSrv.delete(oldToken);
			logger.info("-- Old token has been removed --");
			return new ResponseEntity<>(userDvo.getTokenDvo(), HttpStatus.ACCEPTED);
		} else {
			logger.info(String.format("-- Login KO for %s --", username));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping("/isAuth")
	public HttpStatus isAuth(@RequestHeader Map<String, String> headers, @RequestBody(required = true) String uuidToken) {
		String usernameHeader = null;
		for(Entry<String, String> entry : headers.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(key.equals(AppHeaders.HUSERNAME_HEADER)) {
				logger.info(String.format("-- The user %s is trying to auth -- ", value));
				usernameHeader = value;
				break;
			}
		}
		
		if(Utilities.isNullOrEmpty(usernameHeader)) {
			logger.warn("-- The username is null --");
			return HttpStatus.NO_CONTENT;
		}
		
		boolean isAuth = tokenSrv.checkTokenUserRelation(uuidToken, usernameHeader);
		if(isAuth) {
			logger.info("-- User auth OK --");
			return HttpStatus.ACCEPTED;
		}
		logger.info("-- User auth KO --");
		return HttpStatus.UNAUTHORIZED;
	}
	
	@PostMapping("/add")
	public HttpStatus add(@RequestBody UserDvo reqUserDvo) {
		logger.info(String.format("-- Adding the user -> %s", reqUserDvo.getUsername()));
		userSrv.createNewUser(reqUserDvo, Boolean.TRUE);
		logger.info("-- Created --");
		return HttpStatus.CREATED;
	}
	
	@GetMapping(path = "/alls")
	public ResponseEntity<List<UserDvo>> getAlls() {
		List<UserDvo> result = userSrv.getAllsDvo(true);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete")
	@Override
	public HttpStatus remove(UserDvo reqUserDvo) {
		if(reqUserDvo == null) {
			return HttpStatus.NO_CONTENT;
		}
		String username = reqUserDvo.getUsername();
		logger.info(String.format("-- Removing the user -> %s --", username));
		if(Utilities.isNullOrEmpty(username)) {
			logger.warn("-- The username is null --");
			return HttpStatus.NO_CONTENT;
		}
		UserDvo userDvo = userSrv.findUserByUsername(username);
		if(userDvo == null) return HttpStatus.NOT_FOUND;
		userSrv.delete(userDvo, false);
		logger.info("-- User removed sucess --");
		return HttpStatus.OK;
	}
	
	@PutMapping(path = "/update")
	@Override
	public ResponseEntity<UserDvo> update(UserDvo reqUserDvo) {
		if(reqUserDvo == null) {
			return new ResponseEntity<>(reqUserDvo, HttpStatus.NO_CONTENT);
		}
		String username = reqUserDvo.getUsername();
		if(Utilities.isNullOrEmpty(username)) {
			return new ResponseEntity<>(reqUserDvo, HttpStatus.NO_CONTENT);
		}
		logger.info(String.format("-- The user %s has been updated --", username));
		UserDvo result = userSrv.update(reqUserDvo, false);
		logger.info("-- User updated --");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}