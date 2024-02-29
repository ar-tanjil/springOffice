package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.domain.Deductions;
import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import org.springframework.stereotype.Service;

import java.time.YearMonth;

@Service
public class PayrollMapper {

    public PayrollDto payrollToDto(Payroll payroll) {

        PayrollDto dto = new PayrollDto();


        dto.setId(payroll.getId());
        dto.setPeriod(payroll.getPeriod());
        dto.setNetPay(payroll.getNetPay());


        if (payroll.getEmployee() != null) {
            dto.setEmployeeId(payroll.getEmployee().getId());
        }

        if (payroll.getSalary() != null) {
            Salary salary = payroll.getSalary();
            dto.setBasic(salary.getBasic());
            dto.setProvidentFund(salary.getProvidentFund());
            dto.setMedicalAllowance(salary.getMedicalAllowance());
        }

        if (payroll.getAdditions() != null) {
            Additions additions = payroll.getAdditions();
            dto.setTravelAllowance(additions.getTravelAllowance());
            dto.setBonus(additions.getBonus());
        }

        if (payroll.getDeductions() != null) {
            Deductions deductions = payroll.getDeductions();
            dto.setLoanPayment(deductions.getLoanPayment());
            dto.setUnpaidLeave(deductions.getUnpaidLeave());
            dto.setTax(deductions.getTax());
        }


        return dto;
    }


    public PayrollTable payrollToTable(Payroll payroll) {
        PayrollTable table = new PayrollTable();

        if (payroll.getEmployee() != null) {
            table.setEmployeeId(payroll.getEmployee().getId());
            table.setFirstName(payroll.getEmployee().getFirstName());
        }

        table.setNetPay(payroll.getNetPay());
        table.setPeriod(payroll.getPeriod());

        return table;
    }


}
