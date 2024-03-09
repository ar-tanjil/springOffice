package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Claim;
import com.spring.office.payroll.domain.ClaimStatus;
import com.spring.office.payroll.domain.ClaimType;
import com.spring.office.payroll.dto.ClaimDto;
import com.spring.office.payroll.repo.ClaimRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimMapper claimMapper;
    private final ClaimRepo claimRepo;

    public ClaimDto save(ClaimDto dto){
        var save = claimRepo.save(claimMapper.dtoToClaim(dto));
        return claimMapper.claimToDto(save);
    }

    public List<ClaimDto> getAll(){
        return claimRepo.findAll()
                .stream()
                .map(claimMapper:: claimToDto)
                .toList();
    }

    public List<ClaimDto> getClaimByEmployeeAndPeriod(Long employeeId, LocalDate start, LocalDate end){

        Employee emp  = new Employee();
        emp.setId(employeeId);

        return claimRepo.findByEmployeeAndDateIsBetween(emp, start, end)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }


    public List<ClaimDto> getClaimByPeriodEmployeeStatus(Long empId, ClaimStatus status, LocalDate start, LocalDate end){
        Employee employee = new Employee();
        employee.setId(empId);

        return  claimRepo
                .findByEmployeeAndClaimStatusAndDateIsBetween(employee, status, start, end)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }


    public double allClaimAdditions(Long empId, LocalDate start, LocalDate end){

        Employee emp = new Employee();
        emp.setId(empId);

        var additions = claimRepo.findAllAdditionsByEmployee(emp,start,end,
                ClaimStatus.APPROVED, ClaimType.ADDITIONS);

        if (additions != null){
            return additions;
        }
        return 0;
    }

    public double allClaimDeductions(Long empId, LocalDate start, LocalDate end){

        Employee emp = new Employee();
        emp.setId(empId);

       var deduction = claimRepo.findAllAdditionsByEmployee(emp,start,end,
                ClaimStatus.APPROVED, ClaimType.DEDUCTIONS);

       if (deduction != null){
           return deduction;
       }
       return 0;
    }

    public void paidClaim(Long id){


    }

    public List<ClaimDto> getAllOfEmployee(Long empId) {
        Employee emp = new Employee();
        emp.setId(empId);
        return claimRepo.findByEmployee(emp)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }
}
