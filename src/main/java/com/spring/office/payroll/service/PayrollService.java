package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Additions;
import com.spring.office.payroll.domain.Deductions;
import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.repo.AdditionsRepository;
import com.spring.office.payroll.repo.DeductionsRepository;
import com.spring.office.payroll.repo.PayrollRepository;
import com.spring.office.payroll.repo.SalaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;

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


    public Payroll generatePayroll(Long empId, Integer year, Integer month) {

        Employee emp = new Employee();
        emp.setId(empId);
        YearMonth yearMonth = YearMonth.of(year, month);
        Salary salary = salaryRepository.findByEmployee(emp).orElse(new Salary());
        Additions additions = additionsRepository.findByEmployeeAndPeriod(emp, yearMonth).orElse(new Additions());
        Deductions deductions = deductionsRepository.findByEmployeeAndPeriod(emp, yearMonth).orElse(new Deductions());
//        Additions
        double basic = salary.getBasic();
        double ma = salary.getMedicalAllowance();
        double bonus = additions.getBonus();
        double ta = additions.getTravelAllowance();
//    Deductions
        double loan = deductions.getLoanPayment();
        double unpaidLeave = deductions.getUnpaidLeave();
        double pf = salary.getProvidentFund();

        double grossPay = basic + bonus + ma + ta;
        double taxablePay = grossPay - unpaidLeave;
        double tax = taxService.taxCalculation(taxablePay);
        deductionsService.updateTax(tax, yearMonth, emp);
        double netPay = taxablePay - tax - pf - loan;

        Optional<Payroll> optPayroll = payrollRepository.findByEmployeeAndPeriod(emp, yearMonth);

        if (optPayroll.isPresent()) {
            return null;
        }

        Payroll payroll = new Payroll();

        payroll.setPeriod(yearMonth);
        payroll.setNetPay(netPay);

        if (salary.getId() != null) {
            payroll.setSalary(salary);
        }

        if (additions.getId() != null) {
            payroll.setAdditions(additions);
        }
        if (deductions.getId() != null) {
            payroll.setDeductions(deductions);
        }
        if (emp.getId() != null) {
            payroll.setEmployee(emp);
        }

        return payrollRepository.save(payroll);

    }

    public void updateTax(Long empId, Integer year, Integer month) {

    }

    public PayrollDto getPayrollByEmpAndPeriod(Long empId, Integer year, Integer month) {

        YearMonth period = YearMonth.of(year, month);
        Employee employee = new Employee();
        employee.setId(empId);

       var savePayroll = this.generatePayroll(empId, year, month);

       return payrollMapper.payrollToDto(savePayroll);


    }


}
