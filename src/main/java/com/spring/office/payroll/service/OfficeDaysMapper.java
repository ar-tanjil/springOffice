package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.DayStatus;
import com.spring.office.payroll.domain.OfficeDays;
import com.spring.office.payroll.dto.OfficeDaysDto;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class OfficeDaysMapper {

    public OfficeDays dtoToDays(OfficeDaysDto dto){

        return OfficeDays.builder()
                .id(dto.getId())
                .day(DayOfWeek.valueOf(dto.getDay().toUpperCase()))
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(DayStatus.valueOf(dto.getStatus().toUpperCase()))
                .build();
    }

    public OfficeDaysDto dayToDto(OfficeDays days){
        return OfficeDaysDto.builder()
                .id(days.getId())
                .day(days.getDay().name())
                .startTime(days.getStartTime())
                .endTime(days.getEndTime())
                .status(days.getStatus().name())
                .build();
    }


}
