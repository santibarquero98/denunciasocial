package es.santiagobarquero.denunciasocial.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.santiagobarquero.denunciasocial.api.model.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}
