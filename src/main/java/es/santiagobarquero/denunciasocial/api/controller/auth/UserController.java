package es.santiagobarquero.denunciasocial.api.controller.auth;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.arch.structureproject.applayer.ProjectRESTemplate;
import es.santiagobarquero.denunciasocial.api.dvo.TokenDvo;
import es.santiagobarquero.denunciasocial.api.dvo.UserDvo;
import es.santiagobarquero.denunciasocial.api.service.AuditService;
import es.santiagobarquero.denunciasocial.api.service.UserService;
import es.santiagobarquero.denunciasocial.auxiliary.LogAction;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;

@RestController
@RequestMapping("/rest/user")
public class UserController implements ProjectRESTemplate<UserDvo> {
	
    Logger logger = LogAction.getLogger(UserController.class);
	
	@Autowired
	private UserService userSrv;
	
	@Autowired
	private AuditService auditSrv;
	
	@RequestMapping("/")
	public String throwResponse() {
		InetAddress host = Inet4Address.getLoopbackAddress();
		auditSrv.audit(host.getHostAddress(), null, "OK", "/rest/user/", "OK", "IN");
		return "OK";
	}
	
	@PostMapping("/dologin")
	public ResponseEntity<TokenDvo> doLogin(@RequestBody UserDvo reqUserDvo) {
		logger.info("-- " + reqUserDvo.getUsername() + " is trying to login --");
		String username = reqUserDvo.getUsername();
		String password = reqUserDvo.getPassword();
		UserDvo userDvo = userSrv.findUserByUsername(username);
		if(Utilities.isNull(userDvo)) {
			logger.info("-- Login KO for " + username + " --");
			return new ResponseEntity<TokenDvo>(HttpStatus.NO_CONTENT);
		}
		
		boolean okCredentials = BCrypt.checkpw(password, userDvo.getPassword());
		
		if(okCredentials) {
			logger.info("-- Login OK for " + username + " --");
			return new ResponseEntity<TokenDvo>(userDvo.getTokenDvo(), HttpStatus.ACCEPTED);
		} else {
			logger.info("-- Login KO for " + username + " --");
			return new ResponseEntity<TokenDvo>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@PostMapping("/add")
	public HttpStatus add(@RequestBody UserDvo reqUserDvo) {
		userSrv.createNewUser(reqUserDvo, Boolean.TRUE);
		return HttpStatus.CREATED;
	}
	
	@GetMapping(path = "/alls")
	public ResponseEntity<List<UserDvo>> getAlls() {
		List<UserDvo> result = userSrv.getAllsDvo(false);
		return new ResponseEntity<List<UserDvo>>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete")
	@Override
	public HttpStatus remove(UserDvo reqDvo) {
		UserDvo userDvo = userSrv.findUserByUsername(reqDvo.getUsername());
		if(Utilities.isNull(userDvo)) return HttpStatus.NOT_FOUND;
		userSrv.delete(userDvo);
		return HttpStatus.OK;
	}
	
	@PutMapping(path = "/update")
	@Override
	public ResponseEntity<UserDvo> update(UserDvo reqDvo) {
		UserDvo result = userSrv.update(reqDvo);
		return new ResponseEntity<UserDvo>(result, HttpStatus.OK);
	}
}