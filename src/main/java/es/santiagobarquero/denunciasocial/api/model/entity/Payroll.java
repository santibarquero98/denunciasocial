package es.santiagobarquero.denunciasocial.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.santiagobarquero.arch.structureproject.persistence.IEntity;
import es.santiagobarquero.denunciasocial.api.dvo.PayrollDvo;

@Entity
@Table(name = "payrolls")
public class Payroll implements IEntity<PayrollDvo, Payroll> {

	public Payroll() {
		// empty constructor
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "net_salary")
	private float netSalary;
	
	@ManyToOne()
    @JoinColumn(name = "id_user")
    private User user;
	
	@Override
	public Payroll createNew() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayrollDvo getObjectView(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
