package com.spring.office.payroll.service;

import com.spring.office.payroll.dto.TaxDto;
import com.spring.office.payroll.repo.TaxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final TaxRepository taxRepository;
    private final TaxMapper taxMapper;

    public Double taxCalculation(Double salary) {
        List<TaxDto> taxList = taxRepository.findAll()
                .stream().map(taxMapper::taxToDto).toList();

        Double per = 0D;

        for (TaxDto tax : taxList) {
            if (tax.getMinRange() < salary && tax.getMaxRange() > salary) {
                per = tax.getPercentage();
            }
        }

        return salary * per;

    }


    public TaxDto saveTax(TaxDto dto) {
        var saveTax = taxRepository.save(taxMapper.dtoToTax(dto));
        return taxMapper.taxToDto(saveTax);
    }


}
