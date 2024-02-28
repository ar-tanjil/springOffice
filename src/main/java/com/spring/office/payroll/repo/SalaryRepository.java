package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {


    Optional<Salary> findByEmployee(Employee employee);

}
