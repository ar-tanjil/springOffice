package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.DayStatus;
import com.spring.office.payroll.domain.OfficeDays;
import com.spring.office.payroll.dto.OfficeDaysDto;
import com.spring.office.payroll.repo.OfficeDaysRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeDaysService {

    private final OfficeDaysRepo daysRepo;
    private final OfficeDaysMapper daysMapper;
    public void initialSave(){
        for (int i = 1; i <= 7; i++){
            OfficeDays day = OfficeDays.builder()
                    .day(DayOfWeek.of(i))
                    .startTime(LocalTime.of(10,0, 0))
                    .endTime(LocalTime.of(18, 0, 0))
                    .status(DayStatus.WEEKDAY)
                    .build();

            daysRepo.save(day);

        }

    }


    public List<OfficeDaysDto> getAllDays(){
        return daysRepo.findAll()
                .stream()
                .map(daysMapper::dayToDto)
                .toList();
    }



    public OfficeDaysDto updateDays(OfficeDaysDto dto){
        var update = daysRepo.save(daysMapper.dtoToDays(dto));
        return daysMapper.dayToDto(update);
    }

    public OfficeDaysDto getDaysById(Long id){
        return daysRepo.findById(id).map(daysMapper::dayToDto)
                .orElse(null);
    }

    public List<OfficeDays> getWeekends(){
        return daysRepo.findByStatus(DayStatus.WEEKEND);
    }

    public OfficeDays getOfficeDay(DayOfWeek day){
        return daysRepo.findByDay(day)
                .orElse(null);
    }

}
