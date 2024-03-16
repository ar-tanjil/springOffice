package com.spring.office.payroll.repo;

import com.spring.office.payroll.domain.OfficeRule;
import com.spring.office.payroll.domain.RulesEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OfficeRuleRepo extends JpaRepository<OfficeRule, Long> {


    Optional<OfficeRule> findByName(RulesEnum name);


//    @Modifying
//    @Transactional
    @Query("SELECT r.penalty FROM OfficeRule r " +
            " WHERE r.name = :name")
    Integer getPenalty(RulesEnum name);

}
