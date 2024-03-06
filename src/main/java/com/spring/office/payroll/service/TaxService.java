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
        return salary * per / 100;
    }

    private Double getTaxPer(Double taxablePay) {

        double taxThrashHold = taxablePay * 12;

        List<TaxDto> taxList = taxRepository.findAll()
                .stream().map(taxMapper::taxToDto).toList();

        TaxDto maxMin = taxList.stream()
                .max(Comparator.comparing(TaxDto::getMinRange))
                .orElse(null);

        if (maxMin == null){
            return 0D;
        }

        if (maxMin.getMinRange() < taxThrashHold) {
            return maxMin.getPercentage();
        }

        for (TaxDto tax : taxList) {
            if (taxThrashHold >= tax.getMinRange() && taxThrashHold <= tax.getMaxRange()) {
                return tax.getPercentage();
            }
        }
        return 0D;
    }


    public TaxDto saveTax(TaxDto dto) {
        var saveTax = taxRepository.save(taxMapper.dtoToTax(dto));
        return taxMapper.taxToDto(saveTax);
    }

    public List<TaxDto> getAllTax() {
        List<Tax> taxList = taxRepository.findAll();
        return taxList.stream().map(taxMapper::taxToDto)
                .sorted()
                .toList();
    }

    public TaxDto getById(Long id) {
        Optional<Tax> saveTax = taxRepository.findById(id);
        return saveTax.map(taxMapper::taxToDto).orElse(null);
    }

    public void deleteTax(Long id) {
        taxRepository.deleteById(id);
    }
}
