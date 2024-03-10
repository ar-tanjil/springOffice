package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.ClaimCategory;
import com.spring.office.payroll.domain.ClaimType;
import com.spring.office.payroll.dto.ClaimCategoryDto;
import org.springframework.stereotype.Service;

@Service
public class ClaimCategoryMapper {

    public ClaimCategoryDto claimCategoryToDto(ClaimCategory category){

        return ClaimCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .claimType(category.getClaimType().name())
                .build();
    }


    public ClaimCategory dtoToClaimCategory(ClaimCategoryDto dto){
        return ClaimCategory.builder()
                .id(dto.getId())
                .name(dto.getName())
                .claimType(ClaimType.valueOf(dto.getClaimType().toUpperCase()))
                .build();
    }

}
