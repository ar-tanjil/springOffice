package com.spring.office.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

//    Get Department by emp id


    List<Employee> findAllByDeletedFalse();

    @Query(value = "select * from employee where id = :val and deleted = 0", nativeQuery = true)
    Optional<Employee> findByCustomId(@Param("val") Long id);
//    Iterable<Employee> findByIsDeleted(boolean value);



}
