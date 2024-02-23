package com.spring.office.repo;

import com.spring.office.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepo extends JpaRepository<Application, Long> {

    List<Application> findAllByDeletedFalse();

    Optional<Application> findByIdAndDeletedFalse(Long id);
}
