package com.spring.office.repo;


import com.spring.office.domain.Employee;
import com.spring.office.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {



}
