package es.santiagobarquero.denunciasocial.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.santiagobarquero.arch.structureproject.applayer.ProjectRESTemplate;
import es.santiagobarquero.denunciasocial.api.dvo.PayrollDvo;
import es.santiagobarquero.denunciasocial.api.service.PayrollService;

@RestController
@RequestMapping("/rest/payroll")
public class PayrollController implements ProjectRESTemplate<PayrollDvo>{

	@Autowired
	private PayrollService payrollSrv;
	
	@PostMapping("/add")
	@Override
	public HttpStatus add(PayrollDvo payrollDvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(path = "/alls")
	@Override
	public ResponseEntity<List<PayrollDvo>> getAlls() {
		// TODO Auto-generated method stub
		return null;
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
