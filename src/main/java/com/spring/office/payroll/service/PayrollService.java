package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeService;
import com.spring.office.payroll.domain.Payroll;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.PayrollDto;
import com.spring.office.payroll.dto.PayrollTable;
import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.repo.PayrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    //    Mapper
    private final PayrollMapper payrollMapper;
    private double loan;


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

    public List<PayrollTable> getAllByPeriod(Integer year, Integer month) {

   return generateAllPayroll(year, month).stream()
           .map(payrollMapper::payrollToTable)
           .toList();
    }


    private List<Payroll> generateAllPayroll(int year, int month){

        YearMonth period = YearMonth.of(year, month);

        List<Employee> listEmployee = employeeService.getAllEmployeeOrg();
        List<Payroll> listPayroll = new ArrayList<>();
        for (Employee employee : listEmployee){
            var employeeHireDate = employee.getHireDate();
            YearMonth hireDate = YearMonth.of(employeeHireDate.getYear(), employeeHireDate.getMonth());

            if (hireDate.isAfter(period)){
                break;
            }

            Optional<Payroll> generated = payrollRepository
                    .findByEmployeeAndPeriod(employee,period);

            if (generated.isPresent()){
                listPayroll.add(generated.get());
                break;
            }

            listPayroll.add(generatePayroll(employee, period));

        }

        return listPayroll;

    }



    public Payroll generatePayroll(Employee employee, YearMonth period) {

//        Employee employee = new Employee();
//        employee.setId(empId);
//        Add
        int totalWorkingDay = totalWorkingDay(period);
        int unpaidLeaveDay = unpaidLeaveDay(employee.getId(), period);
        int totalLeaveDay = getAbsenceDays(employee.getId(), period).size();


        SalaryDto salary = salaryService.getSalaryByEmployee(employee.getId());
        double basic = salary.getBasic();
        double unpaidLeave = unpaidLeave(employee.getId(), period, salary.getBasic());
        double providentFund = salary.getProvident();
        double providentInformation = salary.getProvidentFund();
        double travelAllowance = salary.getTravel();
        double travelInformation = salary.getTravelAllowance();
        double medicalAllowance = salary.getMedical();
        double medicalInformation = salary.getMedicalAllowance();
        double loan = salary.getLoan();


        double grossSalary = basic + medicalAllowance
                + travelAllowance - (unpaidLeave + providentFund );
        double tax = taxService.taxCalculation(grossSalary);
        double taxInformation = taxService.getTaxPer(grossSalary);
        double netSalary = grossSalary - tax;

        if (loan < netSalary){
            netSalary -= loan;
            salaryService.updateLoan(employee.getId(), loan);
        } else {
            loan = 0;
        }


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
                .build();


        return payrollRepository.save(payroll);
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
        System.out.println(unpaid);
        for (LocalDate abs : absenceDayList) {
            for (LocalDate gran : grantedAbsenceDay) {
                if (gran == abs) {
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
}
