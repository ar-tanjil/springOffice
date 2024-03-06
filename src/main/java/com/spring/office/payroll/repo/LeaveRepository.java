package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave,Long> {

    List<Leave> findByEmployeeAndDayIsBetween(Employee emp, LocalDate start, LocalDate end);

    List<Leave> findByEmployeeAndDayIsBetweenAndStatusTrue(Employee emp, LocalDate start, LocalDate end);
}
