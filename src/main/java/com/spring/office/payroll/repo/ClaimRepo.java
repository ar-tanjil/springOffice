package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    void changeClaimStatus(Long id, ClaimStatus claimStatus);


    @Modifying
    @Transactional
//    @Query("update Claim c set  c.payroll = :payroll " +
//            " where c.employee = :employee " +
//            " AND c.date BETWEEN :start AND :end AND c.claimStatus = :claimStatus  " +
//            " AND c.claimCategory.claimType = :type ")
    @Query(value = " update claim c   \n" +
            " left join claim_category cc on c.category = cc.id  \n" +
            " set c.payroll_id = :payrollId , \n" +
            " c.claim_status = :newStatus " +
            " where c.employee_id = :empId " +
            " AND c.date BETWEEN :start AND :end " +
            " AND c.claim_status = :claimStatus  " +
            " and cc.claim_type = :type ", nativeQuery = true)
    void changeClaimStatusByPeriod(@Param("empId") Long empId,
                                   @Param("start") LocalDate start,
                                   @Param("end") LocalDate end,
                                   @Param("claimStatus") String claimStatus,
                                   @Param("type") String type,
                                   @Param("payrollId") Long payrollId,
                                   @Param("newStatus") String newStatus);


    @Modifying
    @Transactional
    @Query(value = "update claim c set c.claim_status = :claimStatus " +
            " where  c.payroll_id = :payroll", nativeQuery = true)
    void changeClaimStatusByPayroll(@Param("claimStatus") String claimStatus,
                                    @Param("payroll") Long payroll);



}
