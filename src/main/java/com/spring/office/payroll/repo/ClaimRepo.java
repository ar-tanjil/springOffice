package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Claim;
import com.spring.office.payroll.domain.ClaimStatus;
import com.spring.office.payroll.domain.ClaimType;
import com.spring.office.payroll.dto.ClaimDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ClaimRepo extends JpaRepository<Claim, Long> {
    List<Claim> findByEmployeeAndDateIsBetween(Employee emp, LocalDate start, LocalDate end);

    List<Claim> findByEmployeeAndClaimStatusAndDateIsBetween(Employee employee, ClaimStatus status, LocalDate start, LocalDate end);

    @Query("SELECT SUM(c.amount) FROM Claim c JOIN c.claimCategory r "
            + "WHERE  c.employee = :emp AND c.claimStatus = :status " +
            "AND c.date BETWEEN :start AND :end AND r.claimType = :type")
    Double findAllAdditionsByEmployee(Employee emp, LocalDate start,
                                   LocalDate end, ClaimStatus status,
                                   ClaimType type);

    List<Claim> findByEmployee(Employee emp);
}