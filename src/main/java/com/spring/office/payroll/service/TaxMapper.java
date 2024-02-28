package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Tax;
import com.spring.office.payroll.dto.TaxDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TaxMapper {

    public TaxDto taxToDto(Tax tax){

        return TaxDto.builder()
                .id(tax.getId())
                .percentage(tax.getPercentage())
                .title(tax.getTitle())
                .maxRange(tax.getMaxRange())
                .minRange(tax.getMinRange())
                .build();

    }

    public Tax dtoToTax(TaxDto dto){


        return Tax.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .percentage(dto.getPercentage())
                .minRange(dto.getMinRange())
                .maxRange(dto.getMaxRange())
                .build();
    }


}
