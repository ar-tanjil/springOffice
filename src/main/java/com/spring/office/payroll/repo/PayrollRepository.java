package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.ClaimStatus;
import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.domain.PayrollStatus;
import com.spring.office.payroll.dto.PayrollTable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Period;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Optional<Payroll> findByEmployeeAndPeriod(Employee employee, YearMonth Period);

    int countByEmployeeAndPeriod(Employee employee, YearMonth Period);

    List<Payroll> findByPeriod(YearMonth period);

    @Modifying
    @Transactional
    @Query("delete Payroll p where p.id = :id And p.status = :status")
    void deletePayroll(Long id, PayrollStatus status);

    List<Payroll> findAllByStatusOrderByPeriodDesc(PayrollStatus payrollStatus);


    @Modifying
    @Transactional
    @Query("update Payroll p set p.status = :payrollStatus " +
            " where  p.id = :id")
    void changePayrollStatus(Long id, PayrollStatus payrollStatus);


    Optional<Payroll> findByEmployeeAndPeriodAndStatus(Employee employee,
                                                       YearMonth period,
                                                       PayrollStatus payrollStatus);

    List<Payroll> findByPeriodAndStatus(YearMonth period,
                                            PayrollStatus payrollStatus,
                                            Sort orders);
}
