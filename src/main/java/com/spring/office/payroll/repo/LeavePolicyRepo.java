package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.LeavePolicy;
import com.spring.office.payroll.dto.LeavePolicyDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface LeavePolicyRepo extends JpaRepository<LeavePolicy, Long> {
   Optional<LeavePolicy> findByEmployee(Employee employee);

   @Modifying
   @Transactional
   @Query("update LeavePolicy l set l.medicalSpent = l.medicalSpent + :day where  l.employee = :emp")
   void updateMedicalSpent(Employee emp, int day);

   @Modifying
   @Transactional
   @Query("update LeavePolicy l set l.casualSpent = l.casualSpent + :day where  l.employee = :emp")
   void updateCasualSpent(Employee emp, int day);

   @Modifying
   @Transactional
   @Query("update LeavePolicy l set l.unpaidSpent = l.unpaidSpent + :day where  l.employee = :emp")
   void updateUnpaidSpent(Employee emp, int day);

}
