package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeService;
import com.spring.office.payroll.domain.*;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.repo.PayrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayrollService {


    //Repository
    private final PayrollRepository payrollRepository;

    //   Service
    private final HolidayService holidayService;
    private final AttendanceService attendanceService;
    private final LeaveService leaveService;
    private final SalaryService salaryService;
    private final TaxService taxService;
    private final EmployeeService employeeService;
    private final ClaimService claimService;
    private final OfficeRuleService officeRuleService;
    //    Mapper
    private final PayrollMapper payrollMapper;



    public PayrollDto getPayrollByEmpAndPeriod(Long empId, Integer year, Integer month) {

        YearMonth period = YearMonth.of(year, month);
        Employee employee = new Employee();
        employee.setId(empId);
        Optional<Payroll> findPayroll = payrollRepository.findByEmployeeAndPeriod(employee, period);
        if (findPayroll.isEmpty()) {
            return payrollMapper.payrollToDto(generatePayroll(employee, period));
        }

        return payrollMapper.payrollToDto(findPayroll.get());
    }

    public List<PayrollTable> getPendingPayroll() {

        var pendingPayroll = payrollRepository
                .findAllByStatusOrderByPeriodDesc(PayrollStatus.PENDING);

        return pendingPayroll.stream()
                .map(payrollMapper::payrollToTable)
                .toList();
    }


    public List<PayrollTable> getPaymentPayroll() {

        var pendingPayroll = payrollRepository
                .findAllByStatusOrderByPeriodDesc(PayrollStatus.PAYMENT);

        return pendingPayroll.stream()
                .map(payrollMapper::payrollToTable)
                .toList();
    }

    public void processPayrollByEmployee(Long id, Integer year, Integer month) {
        YearMonth period = YearMonth.of(year, month);
        Employee employee = new Employee();
        employee.setId(id);
        Optional<Payroll> findPayroll = payrollRepository.findByEmployeeAndPeriod(employee, period);
        if (findPayroll.isEmpty()) {
         generatePayroll(employee, period);
        }

    }

    public List<PayrollTable> processAllPayroll(Integer year, Integer month) {

        return generateAllPayroll(year, month).stream()
                .map(payrollMapper::payrollToTable)
                .toList();
    }


    private List<Payroll> generateAllPayroll(int year, int month) {

        YearMonth period = YearMonth.of(year, month);

        List<Employee> listEmployee = employeeService.getAllEmployeeOrg();
        List<Payroll> listPayroll = new ArrayList<>();
        for (Employee employee : listEmployee) {
            var employeeHireDate = employee.getHireDate();
            YearMonth hireDate = YearMonth.of(employeeHireDate.getYear(),
                    employeeHireDate.getMonth());

            if (hireDate.isAfter(period)) {
                break;
            }

            Optional<Payroll> generated = payrollRepository
                    .findByEmployeeAndPeriod(employee, period);

            if (generated.isPresent()) {

                Payroll payroll = generated.get();

                if (payroll.getStatus() == PayrollStatus.PAYMENT) {
                    break;
                }
                listPayroll.add(payroll);
                break;
            }

            listPayroll.add(generatePayroll(employee, period));

        }

        return listPayroll;

    }


    public Payroll generatePayroll(Employee employee, YearMonth period) {

//      Basic Information
        int totalWorkingDay = totalWorkingDay(period);
        int unpaidLeaveDay = unpaidLeaveDay(employee.getId(), period);
        int totalLeaveDay = getAbsenceDays(employee.getId(), period).size();
        int fineDay = totalFineDay(employee.getId(), period);

//        Claim Information
        LocalDate start = LocalDate.of(period.getYear(), period.getMonth(), 1);
        LocalDate end = LocalDate.of(period.getYear(), period.getMonth(), period.lengthOfMonth());


        double reimbursement = claimService.getClaimAmountByPeriodAndEmployee(
                employee, start, end, ClaimStatus.APPROVED, ClaimType.REIMBURSEMENT
        );
        double otherDeduction = claimService
                .getClaimAmountByPeriodAndEmployee(employee, start, end,
                        ClaimStatus.APPROVED, ClaimType.DEDUCTIONS);

        double bonus = claimService
                .getClaimAmountByPeriodAndEmployee(employee, start, end,
                        ClaimStatus.APPROVED, ClaimType.BONUS);


//                Salary Information
        SalaryDto salary = salaryService.getSalaryByEmployee(employee.getId());
        double basic = salary.getBasic();
        double unpaidLeave = unpaidLeave(employee.getId(), period, salary.getBasic());
        double fine = fine(employee.getId(), period, basic);
        double providentFund = salary.getProvident();
        double providentInformation = salary.getProvidentFund();
        double travelAllowance = salary.getTravel();
        double travelInformation = salary.getTravelAllowance();
        double medicalAllowance = salary.getMedical();
        double medicalInformation = salary.getMedicalAllowance();
        double loan = salary.getLoan();


        double grossSalary = basic + medicalAllowance
                + travelAllowance + bonus - (unpaidLeave + providentFund + fine);
        double tax = taxService.taxCalculation(grossSalary);
        double taxInformation = taxService.getTaxPer(grossSalary);
        double netSalary = grossSalary - tax + (reimbursement - otherDeduction);

        if (loan < netSalary) {
            netSalary -= loan;
            salaryService.deductLoan(employee.getId(), loan);
        } else {
            loan = 0;
        }

        salaryService.addEpf(employee.getId(), providentFund);


        Payroll payroll = Payroll.builder()
                .employee(employee)
                .period(period)
                .netSalary(netSalary)
                .grossSalary(grossSalary)
                .basicSalary(basic)
                .medicalAllowance(medicalAllowance)
                .travelAllowance(travelAllowance)
                .providentFund(providentFund)
                .unpaidLeave(unpaidLeave)
                .tax(tax)
                .loanPayment(loan)
                .unpaidLeaveDay(unpaidLeaveDay)
                .workingDay(totalWorkingDay)
                .totalLeaveDay(totalLeaveDay)
                .taxInformation(taxInformation)
                .totalLeaveDay(totalLeaveDay)
                .providentInformation(providentInformation)
                .medicalInformation(medicalInformation)
                .travelInformation(travelInformation)
                .reimbursement(reimbursement)
                .bonusAmount(bonus)
                .otherDeduction(otherDeduction)
                .status(PayrollStatus.PENDING)
                .fine(fine)
                .fineDay(fineDay)
                .build();


        Payroll save = payrollRepository.save(payroll);


        claimService.changeClaimStatusByPeriodAndEmployee(employee, start, end,
                ClaimStatus.APPROVED,
                ClaimType.REIMBURSEMENT, ClaimStatus.ONPROCESS, save.getId());

        claimService.changeClaimStatusByPeriodAndEmployee(employee, start, end,
                ClaimStatus.APPROVED,
                ClaimType.BONUS, ClaimStatus.ONPROCESS, save.getId());

        claimService.changeClaimStatusByPeriodAndEmployee(employee, start, end,
                ClaimStatus.APPROVED,
                ClaimType.DEDUCTIONS, ClaimStatus.ONPROCESS, save.getId());

        return save;
    }


    private int totalFineDay(Long empId, YearMonth period) {
        LocalDate start = LocalDate.of(period.getYear()
                , period.getMonth()
                , 1);

        LocalDate end = LocalDate.of(period.getYear(),
                period.getMonth(),
                period.lengthOfMonth());

        int earlyDay = 0;
        int lateDay = 0;
        int halfDay = 0;

        int early = attendanceService.getTotalEarly(empId, start, end);
        int earlyPenalty = officeRuleService.getPenalty(RulesEnum.EARLY);

        if (early != 0 && earlyPenalty != 0) {
            earlyDay = early / earlyPenalty;
        }


        int late = attendanceService.getTotalLate(empId, start, end);
        int latePenalty = officeRuleService.getPenalty(RulesEnum.LATE);

        if (late != 0 && latePenalty != 0) {
            lateDay = late / latePenalty;
        }

        int half = attendanceService.getTotalHalf(empId, start, end);
        int halfPenalty = officeRuleService.getPenalty(RulesEnum.HALF);

        if (half != 0 && halfPenalty != 0) {
            halfDay = half / halfPenalty;
        }


        return earlyDay + lateDay + halfDay;

    }

    public double fine(Long employeeId, YearMonth period, Double basicSal) {
        int fineDay = totalFineDay(employeeId, period);
        int totalWorkingDay = totalWorkingDay(period);

        return (basicSal / totalWorkingDay) * fineDay;
    }

    private Double unpaidLeave(Long employeeId, YearMonth period, Double basicSal) {

        int totalWorkingDay = totalWorkingDay(period);
        int unpaidLeaveDay = unpaidLeaveDay(employeeId, period);

        return (basicSal / totalWorkingDay) * unpaidLeaveDay;
    }


    private int unpaidLeaveDay(Long empId, YearMonth period) {

        Set<LocalDate> absenceDayList = getAbsenceDays(empId, period);
        Set<LocalDate> grantedAbsenceDay = leaveService.getLeaveGrantedDay(empId, period);

        int unpaid = absenceDayList.size();
        for (LocalDate abs : absenceDayList) {
            for (LocalDate gran : grantedAbsenceDay) {
                if (gran.isEqual(abs)) {
                    unpaid--;
                    break;
                }
            }
        }

        System.out.println(unpaid);
        return unpaid;

    }


    private Set<LocalDate> getAbsenceDays(Long empId, YearMonth period) {

        LocalDate start = LocalDate.of(period.getYear()
                , period.getMonth()
                , 1);


        int year = period.getYear();
        Month month = period.getMonth();


        LocalDate end = LocalDate.of(period.getYear(),
                period.getMonth(),
                period.lengthOfMonth());
        List<AttendanceDto> allAttendance = attendanceService
                .getEmployeePresentDayByMonth(empId, start, end);


        Set<LocalDate> absenceDates = new HashSet<>();

        IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth())
                .mapToObj(day -> LocalDate.of(year, month, day))
                .filter(this::filterHoliday)
                .filter(day -> checkAbsenceDay(day, allAttendance))
                .forEach(absenceDates::add);

        return absenceDates;
    }

    private boolean filterHoliday(LocalDate date) {
        return !holidayService.checkHoliday(date);
    }


    private Set<LocalDate> getTotalHolidayByPeriod(YearMonth period) {
        int year = period.getYear();
        Month month = period.getMonth();
        Set<LocalDate> holidaySet = new HashSet<>();
        IntStream.rangeClosed(1, YearMonth.of(year, month).lengthOfMonth())
                .mapToObj(day -> LocalDate.of(year, month, day))
                .filter(holidayService::checkHoliday)
                .forEach(holidaySet::add);

        return holidaySet;
    }

    private boolean checkAbsenceDay(LocalDate day, List<AttendanceDto> attendanceSheet) {

        for (AttendanceDto att : attendanceSheet) {
            if (day.isEqual(att.getDay())) {
                return false;
            }
        }
        return true;

    }


    private Integer totalWorkingDay(YearMonth period) {
        int dayOfMonth = period.lengthOfMonth();
        return dayOfMonth - getTotalHolidayByPeriod(period).size();
    }


    public void deleteAllPayroll() {
        List<Payroll> listDep = payrollRepository.findAll();

        payrollRepository.deleteAll(listDep);

    }

    public PayrollDto getPayrollById(Long id) {
        return payrollRepository.findById(id)
                .map(payrollMapper::payrollToDto)
                .orElse(null);
    }


    public void deletePayroll(Long id) {
        claimService.updateClaimByPayroll(id, ClaimStatus.APPROVED);
        Payroll payroll = payrollRepository.findById(id).get();

        if (payroll.getEmployee() == null){
         return;
        }
        Long empId = payroll.getEmployee().getId();
        salaryService.addLoan(empId, payroll.getLoanPayment());
        salaryService.deductEpf(empId, payroll.getProvidentFund());
        payrollRepository.deleteById(id);
    }


    public void completePayment(Long id) {
        claimService.updateClaimByPayroll(id, ClaimStatus.PYAMENT);
        payrollRepository.changePayrollStatus(id, PayrollStatus.PAYMENT);
    }

    public List<PayrollTable> getPaymentPayrollByEmp(Long empId, Integer year, Integer month) {

        Employee employee = new Employee();
        employee.setId(empId);

        YearMonth period = YearMonth.of(year, month);

        return payrollRepository
                .findByEmployeeAndPeriodAndStatus(employee,
                        period,
                        PayrollStatus.PAYMENT)
                .map(payrollMapper::payrollToTable)
                .stream().toList();

    }

    public List<PayrollTable> getAllPaymentPayroll(Integer year, Integer month) {
        YearMonth period = YearMonth.of(year, month);

        return payrollRepository
                .findByPeriodAndStatus(period, PayrollStatus.PAYMENT ,
                        Sort.by(Sort.Direction.ASC , "period"))
                .stream()
                .map(payrollMapper::payrollToTable)
                .toList();

    }


}
