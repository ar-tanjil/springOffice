package com.spring.office.payroll.repo;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Attendance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance> findByEmployeeAndDay(Employee employee, LocalDate day);


    List<Attendance> findByEmployeeAndDayIsBetween(Employee emp, LocalDate start, LocalDate end);

    List<Attendance> findByEmployeeAndDayIsBetweenAndPresentTrue(Employee employee, LocalDate start, LocalDate end);

    @Query(value = "select a.employee from Attendance a where a.day BETWEEN :start AND :end")
    Set<Employee> findByPeriod(LocalDate start, LocalDate end);


    @Modifying
    @Transactional
    @Query("update Attendance d set d.present = :val where d.day = :day and d.employee = :emp")
    void updatePresent(boolean val, LocalDate day, Employee emp);

    Integer countByDayAndCheckInIsNotNull(LocalDate localDate);
}
