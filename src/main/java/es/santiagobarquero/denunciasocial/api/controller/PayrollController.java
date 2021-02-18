package es.santiagobarquero.denunciasocial.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.arch.structureproject.applayer.ProjectRESTemplate;
import es.santiagobarquero.denunciasocial.api.dvo.PayrollDvo;
import es.santiagobarquero.denunciasocial.api.service.PayrollService;
import es.santiagobarquero.denunciasocial.api.service.TokenService;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;

@RestController
@RequestMapping("/rest/payroll")
public class PayrollController implements ProjectRESTemplate<PayrollDvo>{

	@Autowired
	private PayrollService payrollSrv;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/findByDate")
	public ResponseEntity<List<PayrollDvo>> findByDate(@RequestHeader Map<String, String> headers, PayrollDvo payrollDvo) {
		boolean isAuth = Utilities.requestIsAuth(headers, tokenService);
		if(isAuth) {
			// TODO: Implement this.
		}
		
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
	}
	
	@PostMapping("/add")
	@Override
	public HttpStatus add(PayrollDvo payrollDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(path = "/alls")
	@Override
	public ResponseEntity<List<PayrollDvo>> getAlls() {
		return new ResponseEntity<List<PayrollDvo>>(payrollSrv.getAllsDvo(false), HttpStatus.OK);
	}

	@DeleteMapping(path = "/delete")
	@Override
	public HttpStatus remove(PayrollDvo payrollDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping(path = "/update")
	@Override
	public ResponseEntity<PayrollDvo> update(PayrollDvo payrollDvo) {
		// TODO Auto-generated method stub
		return null;
	}

}
