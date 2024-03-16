package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.OfficeRule;
import com.spring.office.payroll.domain.RulesEnum;
import com.spring.office.payroll.dto.OfficeRuleDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class OfficeRuleMapper {


    public OfficeRule dtoToRule(OfficeRuleDto dto){

        return OfficeRule.builder()
                .id(dto.getId())
                .name(RulesEnum.valueOf(dto.getName().toUpperCase()))
                .inTime(LocalTime.parse(dto.getInTime()))
                .outTime(LocalTime.parse(dto.getOutTime()))
                .penalty(dto.getPenalty())
                .build();
    }



    public OfficeRuleDto ruleToDto(OfficeRule rule){

        DateTimeFormatter timeFormat = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT);

        String in = timeFormat.format(rule.getInTime());
        String out = timeFormat.format(rule.getOutTime());


        return OfficeRuleDto.builder()
                .id(rule.getId())
                .name(rule.getName().name())
                .inTime(in)
                .outTime(out)
                .penalty(rule.getPenalty())
                .build();

    }




}
