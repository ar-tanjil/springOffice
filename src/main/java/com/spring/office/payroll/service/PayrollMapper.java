package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import org.springframework.stereotype.Service;

@Service
public class PayrollMapper {

    public PayrollDto payrollToDto(Payroll payroll) {

        return PayrollDto.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployee().getId())
                .workingDays(payroll.getWorkingDay())
                .unpaidLeaveDays(payroll.getUnpaidLeaveDay())
                .departmentName(payroll.getEmployee().getDepartment().getDepartmentName())
                .jobTitle(payroll.getEmployee().getJob().getJobTitle())
                .period(payroll.getPeriod())
                .basicSalary(payroll.getBasicSalary())
                .grossSalary(payroll.getGrossSalary())
                .netSalary(payroll.getNetSalary())
                .bonusAmount(payroll.getBonusAmount())
                .otherEarning(payroll.getOtherEarning())
                .otherDeduction(payroll.getOtherDeduction())
                .loanPayment(payroll.getLoanPayment())
                .unpaidLeave(payroll.getUnpaidLeave())
                .tax(payroll.getTax())
                .medicalAllowance(payroll.getMedicalAllowance())
                .providentFund(payroll.getProvidentFund())
                .travelAllowance(payroll.getTravelAllowance())
                .taxInformation(payroll.getTaxInformation())
                .employeeName(payroll.getEmployee().getFirstName() + " " + payroll.getEmployee().getLastName())
                .totalLeaveDays(payroll.getTotalLeaveDay())
                .medicalInformation(payroll.getMedicalInformation())
                .travelInformation(payroll.getTravelInformation())
                .providentInformation(payroll.getProvidentInformation())
                .status(payroll.getStatus().name())
                .build();
    }


    public PayrollTable payrollToTable(Payroll payroll) {
        PayrollTable table = new PayrollTable();
        table.setId(payroll.getId());
        if (payroll.getEmployee() != null) {
            table.setEmployeeId(payroll.getEmployee().getId());
            table.setFirstName(payroll.getEmployee().getFirstName());

            if (payroll.getEmployee().getJob() != null){
                table.setJobTitle(payroll.getEmployee().getJob().getJobTitle());
            }

            if (payroll.getEmployee().getDepartment() != null){
                table.setDepartmentName(payroll.getEmployee().getDepartment().getDepartmentName());
            }
        }

        table.setGrossSalary(payroll.getGrossSalary());
        table.setNetSalary(payroll.getNetSalary());
        table.setPeriod(payroll.getPeriod());

        return table;
    }


}
