package com.spring.office.application;

import com.spring.office.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> findAllByDeletedFalse();

    Optional<Application> findByIdAndDeletedFalse(Long id);
}
