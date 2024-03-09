package com.spring.office.payroll.controller;

import com.spring.office.payroll.domain.LeavePolicy;
import com.spring.office.payroll.dto.LeavePolicyDto;
import com.spring.office.payroll.service.LeavePolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leavePolicies")
@CrossOrigin(origins = "http://localhost:4200")
public class LeavePolicyController {

    private final LeavePolicyService leavePolicyService;

    @GetMapping("/{emp_id}")
    public LeavePolicyDto getLeavePolicyById(
            @PathVariable("emp_id") Long id
    ) {
        return leavePolicyService.getLeavePolicyByEmpId(id);
    }

    @PostMapping()
    public LeavePolicyDto saveLeavePolicy(
            @RequestBody LeavePolicyDto dto
    ) {
        return leavePolicyService.saveLeavePolicy(dto);
    }

    @GetMapping
    public Iterable<LeavePolicyDto> getAllLeave(){
        return  leavePolicyService.getAllLeave();
    }

}
