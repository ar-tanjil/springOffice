package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.dto.AdditionsDto;
import com.spring.office.payroll.repo.AdditionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdditionService {

    private final AdditionsRepository additionsRepository;
    private final AdditionsMapper additionsMapper;

    public AdditionsDto addAddition(
            AdditionsDto dto
    ) {
        Additions additions = additionsRepository.save(additionsMapper.dtoToAdditions(dto));
        return additionsMapper.additionsToDto(additions);
    }

}
