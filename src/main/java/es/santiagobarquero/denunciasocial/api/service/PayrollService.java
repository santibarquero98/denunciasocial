package es.santiagobarquero.denunciasocial.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.santiagobarquero.arch.structureproject.applayer.ServiceInterface;
import es.santiagobarquero.denunciasocial.api.dvo.PayrollDvo;
import es.santiagobarquero.denunciasocial.api.model.entity.Payroll;
import es.santiagobarquero.denunciasocial.api.model.repository.PayrollRepository;
import es.santiagobarquero.denunciasocial.auxiliary.DenunciasocialConstants;
import es.santiagobarquero.denunciasocial.auxiliary.Utilities;

public class PayrollService implements ServiceInterface<PayrollDvo, Payroll> {

	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Autowired
	private PayrollRepository payrollRepository;
	
	// !!!! SERVICE CLASS ONLY CAN INJECT THE REPOSITORY OF THE CLASS REPRESENTED !!!! //

	@Override
	public PayrollDvo create(PayrollDvo payrollDvo, boolean lazy, boolean flushOnFinish) {
		PayrollDvo thisPayrollDvo = payrollDvo;
		Payroll payrollToUpdate = thisPayrollDvo.getEntityObject(lazy);
		thisPayrollDvo = payrollRepository.save(payrollToUpdate).getObjectView(lazy);
		if(flushOnFinish) payrollRepository.flush();
		return thisPayrollDvo;
	}

	@Override
	public void delete(PayrollDvo payrollDvo, boolean flushOnFinish) {
		Payroll payrollToDelete = payrollDvo.getEntityObject(true);
		payrollRepository.delete(payrollToDelete);
		if(flushOnFinish) payrollRepository.flush();
		
	}

	@Override
	public List<PayrollDvo> getAllsDvo(boolean lazy) {
		List<Payroll> result = payrollRepository.findAll();
		List<PayrollDvo> returnValue = new ArrayList<PayrollDvo>(DenunciasocialConstants.ZERO);
		if(Utilities.isNullOrEmpty(result)) {
			return returnValue;
		}
		
		for(Payroll p : result) {
			returnValue.add(p.getObjectView(true));
		}
		
		return returnValue;
	}

	@Override
	public List<Payroll> getAllsEntity() {
		return payrollRepository.findAll();
	}

	@Override
	public PayrollDvo update(PayrollDvo payrollDvo, boolean flushOnFinish) {
		PayrollDvo thisPayrollDvo = payrollDvo;
		Payroll payrollToUpdate = payrollDvo.getEntityObject(true);
		thisPayrollDvo = payrollRepository.save(payrollToUpdate).getObjectView(true);
		if(flushOnFinish) payrollRepository.flush();
		return thisPayrollDvo;
	}
	

}
