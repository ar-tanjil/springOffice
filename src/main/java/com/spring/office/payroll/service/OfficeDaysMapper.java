package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.DayStatus;
import com.spring.office.payroll.domain.OfficeDays;
import com.spring.office.payroll.dto.OfficeDaysDto;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
public class OfficeDaysMapper {

    public OfficeDays dtoToDays(OfficeDaysDto dto){

        return OfficeDays.builder()
                .id(dto.getId())
                .day(DayOfWeek.valueOf(dto.getDay().toUpperCase()))
                .startTime(LocalTime.parse(dto.getStartTime()))
                .endTime(LocalTime.parse(dto.getEndTime()))
                .status(DayStatus.valueOf(dto.getStatus().toUpperCase()))
                .build();
    }

    public OfficeDaysDto dayToDto(OfficeDays days){

        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

        String in = timeFormat.format(days.getStartTime());
        String out = timeFormat.format(days.getEndTime());



        return OfficeDaysDto.builder()
                .id(days.getId())
                .day(days.getDay().name())
                .startTime(in)
                .endTime(out)
                .status(days.getStatus().name())
                .build();
    }


}
