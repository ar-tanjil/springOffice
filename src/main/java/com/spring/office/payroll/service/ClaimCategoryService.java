package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.ClaimCategory;
import com.spring.office.payroll.dto.ClaimCategoryDto;
import com.spring.office.payroll.repo.ClaimCategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimCategoryService {

    private final ClaimCategoryMapper claimCategoryMapper;
    private final ClaimCategoryRepo claimCategoryRepo;


    public ClaimCategoryDto saveClaimCategory(ClaimCategoryDto dto){
        var save =  claimCategoryRepo
                .save(claimCategoryMapper.dtoToClaimCategory(dto));

        return claimCategoryMapper.claimCategoryToDto(save);
    }

    public List<ClaimCategoryDto> getAllClaimCategory(){
        return claimCategoryRepo
                .findAll()
                .stream().map(claimCategoryMapper::claimCategoryToDto)
                .toList();
    }

}
