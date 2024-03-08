package com.spring.office.employee;

import com.spring.office.department.Department;
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

    List<Employee> findAllByDeletedFalseAndSalaryIsNull();

    @Query(value = "select e.id from Employee e WHERE e.deleted != true AND e.active = true")
    List<Long> findAllEmployeeId();

    @Query(value = "select e.firstName from Employee e WHERE e.deleted != true " +
            "AND e.active = true AND e.id = :id")
    String findFirstName(Long id);





    int countByDepartmentAndDeletedFalse(Department department);

    Integer countByDeletedFalse();
}
