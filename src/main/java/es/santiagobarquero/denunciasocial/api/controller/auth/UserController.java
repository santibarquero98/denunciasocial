package es.santiagobarquero.denunciasocial.api.controller.auth;

import java.net.InetSocketAddress;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.FailLoginException;
import es.santiagobarquero.denunciasocial.auxiliary.exceptions.NotImplementedException;

@RestController
@RequestMapping("/rest/user")
public class UserController implements ProjectRESTemplate<UserDvo> {
	
    private Logger logger = null;
	
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
	
	@GetMapping("/")
	public HttpStatus throwResponse(@RequestHeader HttpHeaders reqHeader) {
		InetSocketAddress inetSocket = reqHeader.getHost();
		String hostname = inetSocket.getHostName();
		if(!Utilities.isNullOrBlank(hostname)) {
			try {
				auditSrv.auditGetRequest(hostname, "/rest/user/", true);
			} catch (NotImplementedException e) {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		return HttpStatus.OK;
	}
	
	@PostMapping("/dologin")
	public ResponseEntity<TokenDvo> doLogin(@RequestBody UserDvo reqUserDvo) {
		TokenDvo result = null;
		try {
			result = userSrv.doLogin(reqUserDvo.getUsername(), reqUserDvo.getPassword());
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		} catch (FailLoginException e) {
			return new ResponseEntity<>(result, e.getHttpStatus());
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
		if(Utilities.isNullOrBlank(usernameHeader)) {
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
		try {
			userSrv.createNewUser(reqUserDvo, Boolean.TRUE);
		} catch (ParseException e) {
			logger.info(String.format("Error to convert stringToDate: -> %s", e.getLocalizedMessage()), e);
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
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
		if(Utilities.isNullOrBlank(username)) {
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
		if(Utilities.isNullOrBlank(username)) {
			return new ResponseEntity<>(reqUserDvo, HttpStatus.NO_CONTENT);
		}
		logger.info(String.format("-- The user %s has been updated --", username));
		UserDvo result = userSrv.update(reqUserDvo, false);
		logger.info("-- User updated --");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}