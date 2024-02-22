package com.spring.office.repo;

import com.spring.office.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> findAllByDeletedFalse();
}
