package com.spring.office.payroll.controller;


import com.spring.office.payroll.domain.ClaimCategory;
import com.spring.office.payroll.domain.ClaimStatus;
import com.spring.office.payroll.dto.ClaimCategoryDto;
import com.spring.office.payroll.dto.ClaimDto;
import com.spring.office.payroll.service.ClaimCategoryService;
import com.spring.office.payroll.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/claims")
@CrossOrigin(origins = "http://localhost:4200")
public class ClaimController {

    private final ClaimCategoryService claimCategoryService;
    private final ClaimService claimService;

    @PostMapping("/category")
    public ClaimCategoryDto saveClaimCategory(
            @RequestBody ClaimCategoryDto dto
    ){
        return claimCategoryService.saveClaimCategory(dto);
    }

    @GetMapping("/category")
    public Iterable<ClaimCategoryDto> getAllClaimCategory(){
        return claimCategoryService.getAllClaimCategory();
    }

    @PostMapping
    public void saveClaim(
            @RequestBody ClaimDto dto
    ){
       claimService.save(dto);
    }

    @GetMapping
    public Iterable<ClaimDto> getAllClaim(){
        return claimService.getAll();
    }

    @GetMapping("/{emp_id}")
    public Iterable<ClaimDto> getAllClaimByEmployee(
            @PathVariable("emp_id") Long empId
    ){
        return claimService.getAllOfEmployee(empId);
    }

    @GetMapping("/approved/{claim_id}")
    public boolean approveClaim(
            @PathVariable("claim_id") Long id
    ){
        return claimService.approveClaim(id);
    }

    @GetMapping("/reject/{claim_id}")
    public boolean rejectClaim(
            @PathVariable("claim_id") Long id
    ){
        return claimService.rejectClaim(id);
    }

}
