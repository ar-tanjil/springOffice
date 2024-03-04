package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Holiday;
import com.spring.office.payroll.dto.HolidayDto;
import com.spring.office.payroll.repo.HolidayRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayRepo holidayRepo;


private HolidayDto holidayToDto(Holiday holiday){
    return new HolidayDto(
            holiday.getId(),
            holiday.getDay(),
            holiday.getReason()
    );
}

private Holiday dtoToHoliday(HolidayDto dto){
    Holiday holiday = new Holiday();
    holiday.setId(dto.id());
    holiday.setDay(dto.day());
    holiday.setReason(dto.reason());
    return holiday;
}


public HolidayDto addHoliday(HolidayDto dto){
    var holiday = dtoToHoliday(dto);
    var check = holidayRepo.findByDay(holiday.getDay());
    if (check.isPresent()){
        return null;
    }

    var saveHoliday = holidayRepo.save(holiday);
    return holidayToDto(saveHoliday);
}


public List<HolidayDto> getAllHoliday(){
    return holidayRepo.findAll().stream().map(this::holidayToDto)
            .collect(Collectors.toList());
}

public HolidayDto getHolidayById(Long id){
    return holidayRepo.findById(id)
            .map(this::holidayToDto).orElse(null);
}

public HolidayDto updateHoliday(HolidayDto dto){
    var saveHoliday = holidayRepo.save(dtoToHoliday(dto));
    return this.holidayToDto(saveHoliday);
}

public void deleteHoliday(Long id){
    holidayRepo.deleteById(id);
}


public HolidayDto getHolidayByDay(LocalDate date){
    var holiday = holidayRepo.findByDay(date);

    if (holiday.isPresent()){
        return holidayToDto(holiday.get());
    }
    return null;
}

public boolean checkHoliday(LocalDate date){
    var holiday = getHolidayByDay(date);
    var isHoliday = false;

    if (holiday != null){
        isHoliday = true;
    }

    var weekend = date.getDayOfWeek() == DayOfWeek.FRIDAY ||
            date.getDayOfWeek() == DayOfWeek.SATURDAY;

    return isHoliday || weekend;

}

}
