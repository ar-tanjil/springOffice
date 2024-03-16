package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.OfficeRule;
import com.spring.office.payroll.domain.RulesEnum;
import com.spring.office.payroll.dto.OfficeRuleDto;
import com.spring.office.payroll.repo.OfficeRuleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OfficeRuleService {

    private final OfficeRuleRepo ruleRepo;
    private final OfficeRuleMapper ruleMapper;

    public void initialSave(){

        Set<RulesEnum> rules = EnumSet.of(
                RulesEnum.GRACE,
                RulesEnum.EARLY,
                RulesEnum.LATE,
                RulesEnum.HALF

        );

        for (RulesEnum rule: rules ){
            OfficeRule officeRule = OfficeRule.builder()
                    .name(rule)
                    .inTime(LocalTime.of(0, 0))
                    .outTime(LocalTime.of(0,0))
                    .build();
            ruleRepo.save(officeRule);
        }
    }


    public List<OfficeRuleDto> getAllRules(){
        return ruleRepo.findAll()
                .stream().map(ruleMapper::ruleToDto)
                .toList();
    }


    public OfficeRuleDto getRuleById(Long id) {
        return ruleRepo.findById(id)
                .map(ruleMapper::ruleToDto)
                .orElse(null);
    }


    public OfficeRuleDto update(OfficeRuleDto dto) {
        var update = ruleRepo.save(ruleMapper.dtoToRule(dto));
        return ruleMapper.ruleToDto(update);
    }

    public OfficeRule getRuleByName(RulesEnum name){
        return ruleRepo.findByName(name)
                .orElse(null);
    }

    public Integer getPenalty(RulesEnum name){
        return ruleRepo.getPenalty(name);
    }


}
