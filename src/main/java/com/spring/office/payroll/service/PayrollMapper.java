package com.spring.office.payroll.service;

import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class PayrollMapper {

    public PayrollDto payrollToDto(Payroll payroll) {


        DecimalFormat df = new DecimalFormat("#.##");

        Double netSalary = payroll.getNetSalary();
        netSalary = Double.valueOf(df.format(netSalary));
        Double grossSalary = payroll.getNetSalary();
        grossSalary = Double.valueOf(df.format(grossSalary));
        Double basicSalary = payroll.getBasicSalary();
        basicSalary = Double.valueOf(df.format(basicSalary));
        Double unpaidLeave = payroll.getUnpaidLeave();
        unpaidLeave = Double.valueOf(df.format(unpaidLeave));
        Double loanPayment = payroll.getLoanPayment();
        loanPayment = Double.valueOf(df.format(loanPayment));
        Double tax = payroll.getTax();
        tax = Double.valueOf(df.format(tax));
        Double travelAllowance = payroll.getTravelAllowance();
        travelAllowance = Double.valueOf(df.format(travelAllowance));
        Double medicalAllowance = payroll.getMedicalAllowance();
        medicalAllowance = Double.valueOf(df.format(medicalAllowance));
        Double providentFund = payroll.getProvidentFund();
        providentFund = Double.valueOf(df.format(providentFund));
        Double bonusAmount = payroll.getBonusAmount();
        bonusAmount = Double.valueOf(df.format(bonusAmount));
        Double reimbursement = payroll.getReimbursement();
        reimbursement = Double.valueOf(df.format(reimbursement));
        Double otherDeduction = payroll.getOtherDeduction();
        otherDeduction = Double.valueOf(df.format(otherDeduction));
        Double fine = payroll.getFine();
        fine = Double.valueOf(df.format(fine));


        return PayrollDto.builder()
                .id(payroll.getId())
                .employeeId(payroll.getEmployee().getId())
                .workingDay(payroll.getWorkingDay())
                .unpaidLeaveDay(payroll.getUnpaidLeaveDay())
                .departmentName(payroll.getEmployee().getDepartment().getDepartmentName())
                .jobTitle(payroll.getEmployee().getJob().getJobTitle())
                .period(payroll.getPeriod())
                .basicSalary(basicSalary)
                .grossSalary(grossSalary)
                .netSalary(netSalary)
                .bonusAmount(bonusAmount)
                .reimbursement(reimbursement)
                .otherDeduction(otherDeduction)
                .loanPayment(loanPayment)
                .unpaidLeave(unpaidLeave)
                .tax(tax)
                .medicalAllowance(medicalAllowance)
                .providentFund(providentFund)
                .travelAllowance(travelAllowance)
                .taxInformation(payroll.getTaxInformation())
                .employeeName(payroll.getEmployee().getFirstName() + " " + payroll.getEmployee().getLastName())
                .totalLeaveDay(payroll.getTotalLeaveDay())
                .medicalInformation(payroll.getMedicalInformation())
                .travelInformation(payroll.getTravelInformation())
                .providentInformation(payroll.getProvidentInformation())
                .status(payroll.getStatus().name())
                .fine(fine)
                .fineDay(payroll.getFineDay())
                .build();
    }


    public PayrollTable payrollToTable(Payroll payroll) {
        PayrollTable table = new PayrollTable();
        table.setId(payroll.getId());
        if (payroll.getEmployee() != null) {
            table.setEmployeeId(payroll.getEmployee().getId());
            table.setFirstName(payroll.getEmployee().getFirstName());

            if (payroll.getEmployee().getJob() != null) {
                table.setJobTitle(payroll.getEmployee().getJob().getJobTitle());
            }

            if (payroll.getEmployee().getDepartment() != null) {
                table.setDepartmentName(payroll.getEmployee().getDepartment().getDepartmentName());
            }
        }

        table.setGrossSalary(payroll.getGrossSalary());
        table.setNetSalary(payroll.getNetSalary());
        table.setPeriod(payroll.getPeriod());
        table.setStatus(payroll.getStatus().toString());

        return table;
    }


}
