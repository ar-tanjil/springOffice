package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Deductions;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.YearMonth;
import java.util.Optional;

public interface DeductionsRepository  extends JpaRepository<Deductions, Long> {

    Optional<Deductions> findByEmployeeAndPeriod(Employee employee, YearMonth period);


    @Modifying
    @Transactional
    @Query("update Deductions d set d.tax = :tax where d.period = :period and d.employee = :emp")
    void updateTax(double tax, YearMonth period, Employee emp);

}
