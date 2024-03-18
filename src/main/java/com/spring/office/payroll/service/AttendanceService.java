package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeRepo;
import com.spring.office.payroll.domain.*;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceSheet;
import com.spring.office.payroll.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final EmployeeRepo employeeRepo;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final HolidayService holidayService;
    private final OfficeDaysService officeDaysService;
    private final OfficeRuleService officeRuleService;



    public AttendanceDto giveAttendance(Long empId) {

        Employee emp = new Employee();
        emp.setId(empId);
        LocalDateTime dayTime = LocalDateTime.now();
        LocalDate day = dayTime.toLocalDate();


        if (holidayService.checkHoliday(day)){
            return null;
        }

        Optional<Attendance> optAtt = attendanceRepository.findByEmployeeAndDay(emp, day);

        if (optAtt.isEmpty()) {
            Attendance att = new Attendance();

            att.setDay(day);
            att.setEmployee(emp);
            att.setCheckInStatus(getAttendanceStatus(dayTime, "checkIn"));
            att.setCheckIn(LocalTime.now());

            var saveAtt = attendanceRepository.save(att);
            return attendanceMapper.attendanceToDto(saveAtt);
        }


        var updateAtt = optAtt.get();
        updateAtt.setCheckOut(LocalTime.now());
        updateAtt.setCheckOutStatus(getAttendanceStatus(dayTime, "checkOut"));
        attendanceRepository.save(updateAtt);

        return attendanceMapper.attendanceToDto(updateAtt);
    }


    private AttendanceStatus getAttendanceStatus(LocalDateTime dateTime, String check){

        AttendanceStatus status = AttendanceStatus.OK;
        LocalTime time = dateTime.toLocalTime();
        OfficeRule grace = officeRuleService.getRuleByName(RulesEnum.GRACE);
        OfficeRule early = officeRuleService.getRuleByName(RulesEnum.EARLY);
        OfficeRule late = officeRuleService.getRuleByName(RulesEnum.LATE);
        OfficeRule half = officeRuleService.getRuleByName(RulesEnum.HALF);

        if (check.equalsIgnoreCase("checkIn")){
           if (time.isAfter(late.getInTime())){
               status = AttendanceStatus.HALF;
           } else if (time.isAfter(grace.getInTime())){
               status = AttendanceStatus.LATE;
           }

        } else {
           if (time.isBefore(early.getOutTime())){
               status = AttendanceStatus.HALF;
           } else if(time.isBefore(grace.getOutTime())){
               status = AttendanceStatus.EARLY;
           }
        }

        return status;

    }

    public List<AttendanceDto> getEmployeeAttendanceByMonth(Long empId, LocalDate start, LocalDate end) {
        Employee emp = new Employee();
        emp.setId(empId);

        List<Attendance> listAtt = attendanceRepository
                .findByEmployeeAndDayIsBetween(emp, start, end);

        return attendanceMapper.attendanceToDtoList(listAtt);
    }

    public List<AttendanceDto> getEmployeePresentDayByMonth(Long empId,
                                                            LocalDate start,
                                                            LocalDate end){
        Employee emp = new Employee();
        emp.setId(empId);

        List<Attendance> listAtt = attendanceRepository
                .findByEmployeeAndDayIsBetween(emp,
                        start,
                        end);

        return attendanceMapper.attendanceToDtoList(listAtt);
    }

    public int getTotalEarly(Long empId,
                             LocalDate start,
                             LocalDate end){
        return countCheckInStatus(empId,
                start,
                end,
                AttendanceStatus.EARLY);
    }
    public int getTotalLate(Long empId,
                             LocalDate start,
                             LocalDate end){
        return countCheckInStatus(empId,
                start,
                end,
                AttendanceStatus.LATE);
    }

    public int getTotalHalf(
            Long empId,
            LocalDate start,
            LocalDate end
    ){
        int checkInLate = countCheckInStatus(empId,
                start,
                end,
                AttendanceStatus.HALF);
        int checkOutLate = countCheckOutStatus(empId,
                start,
                end,
                AttendanceStatus.HALF);

        return checkInLate + checkOutLate;
    }

    private int countCheckInStatus(Long empId,
                                     LocalDate start,
                                     LocalDate end,
                                     AttendanceStatus status
                                     ){
        Employee employee = new Employee();
        employee.setId(empId);
        return attendanceRepository
                .countByCheckInStatusAndEmployeeAndDayIsBetween(status,
                employee, start, end);
    }

    private int countCheckOutStatus(Long empId,
                                   LocalDate start,
                                   LocalDate end,
                                   AttendanceStatus status
    ){
        Employee employee = new Employee();
        employee.setId(empId);
        return attendanceRepository
                .countByCheckOutStatusAndEmployeeAndDayIsBetween(status,
                        employee, start, end);
    }


    private boolean getPresentFromAttendance(Attendance attendance) {

        return attendance.getCheckInStatus() == AttendanceStatus.OK ||
                attendance.getCheckOutStatus() == AttendanceStatus.OK;
    }


    private AttendanceSheet getAttendanceSheet(List<Attendance> list, String firstName,
                                               LocalDate end) {

        AttendanceSheet table = new AttendanceSheet();


        boolean endDate = LocalDate.now().isAfter(end);

        int size = endDate ? end.getDayOfMonth() : LocalDate.now().getDayOfMonth();

        boolean[] present = new boolean[size];


        for (Attendance att : list) {

            int index = att.getDay().getDayOfMonth() - 1;

            present[index] = getPresentFromAttendance(att);

        }

        table.setFirstName(firstName);

        table.setPresent(present);

        return table;
    }


    public List<AttendanceSheet> getAttendanceSheet(LocalDate start, LocalDate end) {

        List<Long> empId = employeeRepo.findAllEmployeeId();

        List<AttendanceSheet> table = new ArrayList<>();

        for (Long id : empId) {
            Employee emp = new Employee();
            emp.setId(id);
            List<Attendance> list = attendanceRepository
                    .findByEmployeeAndDayIsBetween(emp, start, end);

            String firstName = employeeRepo.findFirstName(id);

            table.add(getAttendanceSheet(list, firstName, end));
        }

        return table;

    }

    public AttendanceDto getAttendanceByDay(Long id, LocalDate date){

        Employee emp = new Employee();
        emp.setId(id);

        var save = attendanceRepository.findByEmployeeAndDay(emp,date);
        return save.map(attendanceMapper::attendanceToDto).orElse(null);
    }

    public Integer todayPresentEmployee(LocalDate localDate) {

        return attendanceRepository.countByDayAndCheckInIsNotNull(localDate);

    }


    public List<AttendanceDto> getAttendanceLog(LocalDate start, LocalDate end){
        List<Attendance> listLog = attendanceRepository.findByDayIsBetween(start, end);
        return listLog.stream().map(attendanceMapper::attendanceToDto)
                .toList();
    }

    public void giveAttendanceByEmployee(Long empId) {

        Employee emp = new Employee();
        emp.setId(empId);

        LocalDateTime dayTime = LocalDateTime.now();

        Month thisMonth = dayTime.getMonth();

        for (int i = 1; i <= dayTime.getDayOfMonth(); i++){
            var makeDayTime = LocalDateTime.of(2024, thisMonth, i, 10, 0,0);
            LocalDate day = makeDayTime.toLocalDate();

            if (holidayService.checkHoliday(day)){
                break;
            }

            Optional<Attendance> optAtt = attendanceRepository.findByEmployeeAndDay(emp, day);

            if (optAtt.isEmpty()){

                Attendance att = new Attendance();

                att.setDay(day);
                att.setEmployee(emp);
                att.setCheckInStatus(getAttendanceStatus(makeDayTime, "checkIn"));
                att.setCheckIn(makeDayTime.toLocalTime());
                attendanceRepository.save(att);
            }




        }

        for (int i = 1; i <= dayTime.getDayOfMonth(); i++){
           var makeDayTime = LocalDateTime.of(2024, thisMonth, i, 19, 0,0);
            LocalDate day = makeDayTime.toLocalDate();

            Optional<Attendance> optAtt = attendanceRepository.findByEmployeeAndDay(emp, day);

            if (optAtt.isEmpty()){
                break;
            }

            var updateAtt = optAtt.get();
            updateAtt.setCheckOut(makeDayTime.toLocalTime());
            updateAtt.setCheckOutStatus(getAttendanceStatus(makeDayTime, "checkOut"));
            attendanceRepository.save(updateAtt);

            attendanceMapper.attendanceToDto(updateAtt);
        }


    }
}
