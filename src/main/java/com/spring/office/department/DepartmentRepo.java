package com.spring.office.department;


import com.spring.office.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    List<Department>  findAllByDeletedFalse();


}
