package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.*;
import com.spring.office.payroll.dto.ClaimDto;
import com.spring.office.payroll.repo.ClaimRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ClaimMapper claimMapper;
    private final ClaimRepo claimRepo;
    private final SalaryService salaryService;

    public void save(ClaimDto dto) {
        var save = claimRepo.save(claimMapper.dtoToClaim(dto));
    }

    public List<ClaimDto> getAll() {
        return claimRepo.findAll()
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }

    public List<ClaimDto> getClaimByEmployeeAndPeriod(Long employeeId, LocalDate start, LocalDate end) {

        Employee emp = new Employee();
        emp.setId(employeeId);

        return claimRepo.findByEmployeeAndDateIsBetween(emp, start, end)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }


    public List<ClaimDto> getClaimByPeriodEmployeeStatus(Long empId, ClaimStatus status, LocalDate start, LocalDate end) {
        Employee employee = new Employee();
        employee.setId(empId);

        return claimRepo
                .findByEmployeeAndClaimStatusAndDateIsBetween(employee, status, start, end)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }


    public double reimbursementClaimByPeriod(Long empId, LocalDate start, LocalDate end) {

        Employee emp = new Employee();
        emp.setId(empId);

        var reimbursement = claimRepo.findAllAdditionsByEmployee(emp, start, end,
                ClaimStatus.APPROVED, ClaimType.REIMBURSEMENT);

        if (reimbursement != null) {
            return reimbursement;
        }
        return 0;
    }

    public double bonusByPeriod(Long empId, LocalDate start, LocalDate end) {
        Employee emp = new Employee();
        emp.setId(empId);

        var bonus = claimRepo.findAllAdditionsByEmployee(emp, start, end,
                ClaimStatus.APPROVED, ClaimType.BONUS);

        if (bonus != null) {
            return bonus;
        }
        return 0;
    }


    public double getClaimAmountByPeriodAndEmployee(Employee emp, LocalDate start,
                                                    LocalDate end, ClaimStatus status,
                                                    ClaimType type) {


        var amount = claimRepo.findAllAdditionsByEmployee(emp, start, end,
                status, type);

        if (amount != null) {
            return amount;
        }
        return 0;
    }

    public void changeClaimStatusByPeriodAndEmployee(Employee employee,
                                                     LocalDate start,
                                                     LocalDate end,
                                                     ClaimStatus claimStatus,
                                                     ClaimType type,
                                                     ClaimStatus newStatus,
                                                     Long payrollId) {
        claimRepo.changeClaimStatusByPeriod(employee.getId(), start, end,
                claimStatus.toString(), type.toString(), payrollId, newStatus.toString());
    }


    public void updateClaimByPayroll(Long payrollId, ClaimStatus status) {
        claimRepo.changeClaimStatusByPayroll(status.toString(),
                payrollId);
    }


    public double allClaimDeductions(Long empId, LocalDate start, LocalDate end) {

        Employee emp = new Employee();
        emp.setId(empId);

        var deduction = claimRepo.findAllAdditionsByEmployee(emp, start, end,
                ClaimStatus.APPROVED, ClaimType.DEDUCTIONS);

        if (deduction != null) {
            return deduction;
        }
        return 0;
    }

    public void paidClaim(Long id) {


    }

    public List<ClaimDto> getAllOfEmployee(Long empId) {
        Employee emp = new Employee();
        emp.setId(empId);
        return claimRepo.findByEmployee(emp)
                .stream()
                .map(claimMapper::claimToDto)
                .toList();
    }

    public boolean approveClaim(Long id) {
        Claim claim = claimRepo.findById(id).orElse(null);
        if (claim == null) {
            return false;
        }

        if (claim.getClaimStatus() == ClaimStatus.PENDING ||
                claim.getClaimStatus() == ClaimStatus.REJECTED) {
            claimRepo.changeClaimStatus(id, ClaimStatus.APPROVED);

            if (claim.getClaimCategory().getClaimType() == ClaimType.LOAN){
                return approveLoan(claim);
            }

            return true;
        }

        return false;
    }

    private boolean approveLoan(Claim claim){
        if (claim.getEmployee() == null){
            return false;
        }
        Employee employee = claim.getEmployee();
        salaryService.addLoan(employee.getId(), claim.getAmount());
        return true;
    }

    public boolean rejectClaim(Long id) {

        Claim claim = claimRepo.findById(id).orElse(null);

        if (claim == null) {
            return false;
        }

        if (claim.getClaimStatus() == ClaimStatus.ONPROCESS ||
                claim.getClaimStatus() == ClaimStatus.PYAMENT) {
            return false;
        }

        claimRepo.changeClaimStatus(id, ClaimStatus.REJECTED);

        if (claim.getClaimStatus() == ClaimStatus.APPROVED &&
                claim.getClaimCategory().getClaimType() == ClaimType.LOAN){
            return rejectLoan(claim);
        }

        return true;
    }

    private boolean rejectLoan(Claim claim){
        if (claim.getEmployee() == null){
            return false;
        }
        Employee employee = claim.getEmployee();
        salaryService.deductLoan(employee.getId(), claim.getAmount());
        return true;
    }


}
