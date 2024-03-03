package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Tax;
import com.spring.office.payroll.dto.TaxDto;
import com.spring.office.payroll.repo.TaxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxService {

    private final TaxRepository taxRepository;
    private final TaxMapper taxMapper;

    public Double taxCalculation(Double salary) {
        Double per = getTaxPer(salary);
        return salary * per;
    }

    private Double getTaxPer(Double taxablePay) {
        List<TaxDto> taxList = taxRepository.findAll()
                .stream().map(taxMapper::taxToDto).toList();

        TaxDto currentMax = taxList.stream()
                .max(Comparator.comparing(TaxDto::getMaxRange))
                .orElseThrow();

        if (currentMax.getMaxRange() < taxablePay) {
            return currentMax.getPercentage();
        }

        for (TaxDto tax : taxList) {
            if (tax.getMinRange() <= taxablePay && tax.getMaxRange() >= taxablePay) {
                return tax.getPercentage();
            }
        }
        return 0D;
    }


    public TaxDto saveTax(TaxDto dto) {
        var saveTax = taxRepository.save(taxMapper.dtoToTax(dto));
        return taxMapper.taxToDto(saveTax);
    }

    public List<TaxDto> getAllTax(){
        List<Tax> taxList = taxRepository.findAll();
        return taxList.stream().map(taxMapper::taxToDto)
                .toList();
    }

    public TaxDto getById(Long id){
        Optional<Tax> saveTax = taxRepository.findById(id);
       return saveTax.map(taxMapper::taxToDto).orElse(null);
    }

}
