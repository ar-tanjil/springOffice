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
                .build();
    }


    public PayrollTable payrollToTable(Payroll payroll) {
        PayrollTable table = new PayrollTable();
        table.setId(payroll.getId());
        if (payroll.getEmployee() != null) {
            table.setEmployeeId(payroll.getEmployee().getId());
            table.setFirstName(payroll.getEmployee().getFirstName());

            if (payroll.getEmployee().getDepartment() != null){
                table.setJobTitle(payroll.getEmployee().getJob().getJobTitle());
            }

        }

        table.setGrossSalary(payroll.getGrossSalary());
        table.setNetSalary(payroll.getNetSalary());
        table.setPeriod(payroll.getPeriod());

        return table;
    }


}
