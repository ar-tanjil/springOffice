package com.spring.office.repo;


import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    List<Job> findAllByDeletedFalse();

    List<Job> findAllByDepartment(Department department);

    List<Job> findAllByDepartmentAndVacancyIsGreaterThan(Department dep, Integer vacancy);

    List<Job> findAllByVacancyIsGreaterThan(Integer vacancy);

    Optional<Job> findByIdAndVacancyIsGreaterThan(Long id, int vacancy);

    @Modifying
    @Transactional
    @Query("update Job j set j.vacancy = j.vacancy - :req where j.id = :id")
    void reduceVacancy(int req, Long id);

    @Modifying
    @Transactional
    @Query("update Job j set j.vacancy = j.vacancy + :req where j.id = :id")
    void addVacancy(int req, Long id);

    @Modifying
    @Transactional
    @Query("update Job j set j.totalPost = j.totalPost + :add, j.vacancy = j.vacancy + :add where j.id = :jobId and j.department = :dep")
    void addTotalPost(int add, long jobId, Department dep);

    @Modifying
    @Transactional
    @Query("update Job j set j.totalPost = j.totalPost - :reduce, j.vacancy = j.vacancy - :reduce where j.id = :jobId and j.department = :dep")
    void reduceTotalPost(int reduce, long jobId, Department dep);
}
