package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.*;
import com.spring.office.payroll.dto.ClaimDto;
import jakarta.transaction.Transactional;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Transactional
    @Query("update Claim c set c.claimStatus = :claimStatus where  c.id = :id")
    void updateClaim(Long id, ClaimStatus claimStatus);


    @Modifying
    @Transactional
    @Query("update Claim c set  c.payroll = :payroll " +
            " where  c.employee = :employee " +
            " AND c.date BETWEEN :start " +
            " AND :end AND c.claimStatus = :claimStatus  " +
            " AND c.claimCategory.claimType = :type ")
    void updateClaimByPeriod(Employee employee, LocalDate start, LocalDate end,
                             ClaimStatus claimStatus,
                             ClaimType type,  Payroll payroll);



    @Modifying
    @Transactional
    @Query("update Claim c set c.claimStatus = :claimStatus where  c.payroll = :payroll")
    void updateClaimByPeriodAndStatus(ClaimStatus claimStatus, Payroll payroll);
}
