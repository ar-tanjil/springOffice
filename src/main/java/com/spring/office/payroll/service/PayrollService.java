package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.domain.Deductions;
import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import com.spring.office.payroll.repo.AdditionsRepository;
import com.spring.office.payroll.repo.DeductionsRepository;
import com.spring.office.payroll.repo.PayrollRepository;
import com.spring.office.payroll.repo.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollService {

    //Repository
    private final SalaryRepository salaryRepository;
    private final DeductionsRepository deductionsRepository;
    private final AdditionsRepository additionsRepository;
    private final PayrollRepository payrollRepository;

    //   Service
    private final TaxService taxService;
    private final DeductionsService deductionsService;

    //    Mapper
    private final PayrollMapper payrollMapper;


    public Payroll generatePayroll(Employee emp, YearMonth yearMonth) {
        Salary salary = salaryRepository.findByEmployee(emp).orElse(new Salary());
        Additions additions = additionsRepository.findByEmployeeAndPeriod(emp, yearMonth).orElse(new Additions());
        Deductions deductions = deductionsRepository.findByEmployeeAndPeriod(emp, yearMonth).orElse(new Deductions());
//        Additions
        double basic = salary.getBasic();
        double medicalAllowance = salary.getMedicalAllowance();
        double bonus = additions.getBonus();
        double travelAllowance = additions.getTravelAllowance();
//    Deductions
        double loan = deductions.getLoanPayment();
        double unpaidLeave = deductions.getUnpaidLeave();
        double providentFund = salary.getProvidentFund();

        double grossPay = basic + bonus + medicalAllowance + travelAllowance;
        double taxablePay = grossPay - unpaidLeave;
        double tax = taxService.taxCalculation(taxablePay);


        double netPay = taxablePay - tax - providentFund - loan;

        Payroll payroll = payrollRepository
                .findByEmployeeAndPeriod(emp,yearMonth)
                .orElse(null);


        if (payroll == null) {
            payroll = new Payroll();
            payroll.setPeriod(yearMonth);
        }


        payroll.setNetPay(netPay);

        if (salary.getId() != null) {
            payroll.setSalary(salary);
        }

        if (additions.getId() != null) {
            payroll.setAdditions(additions);
        }
        if (deductions.getId() != null) {
            deductions.setTax(tax);
            payroll.setDeductions(deductions);

        }
        if (emp.getId() != null) {
            payroll.setEmployee(emp);
        }

        deductionsService.updateTax(tax, yearMonth, emp);

        return payrollRepository.save(payroll);

    }


    public PayrollDto getPayrollByEmpAndPeriod(Long empId, Integer year, Integer month) {

        YearMonth period = YearMonth.of(year, month);
        Employee employee = new Employee();
        employee.setId(empId);

        var savePayroll = this.generatePayroll(employee, period);

        return payrollMapper.payrollToDto(savePayroll);
    }

    public List<PayrollTable> getAllByPeriod(Integer year, Integer month){
        YearMonth period = YearMonth.of(year, month);
        List<Payroll> optPay = payrollRepository.findByPeriod(period);

        return optPay.stream().map(payrollMapper::payrollToTable)
                .collect(Collectors.toList());

    }


}
