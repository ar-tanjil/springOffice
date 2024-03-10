package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeRepo;
import com.spring.office.payroll.domain.Attendance;
import com.spring.office.payroll.domain.AttendanceStatus;
import com.spring.office.payroll.domain.OfficeDays;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceSheet;
import com.spring.office.payroll.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final EmployeeRepo employeeRepo;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final HolidayService holidayService;
    private final OfficeDaysService officeDaysService;



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

        OfficeDays weekDay = officeDaysService.getOfficeDay(dateTime.getDayOfWeek());
        LocalTime time = LocalTime.now();
        if (check.equalsIgnoreCase("checkIn")){
            time = weekDay.getStartTime();
        } else {
            time = weekDay.getEndTime();
        }

        AttendanceStatus status = AttendanceStatus.ONTIME;

        if (time.isBefore(dateTime.toLocalTime())
                && check.equalsIgnoreCase("checkIn")){
            status = AttendanceStatus.LATE;
        }

        if (time.isAfter(dateTime.toLocalTime())
       && check.equalsIgnoreCase("checkOut")){
            status = AttendanceStatus.EARLYLEAVE;
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

    public List<AttendanceDto> getEmployeePresentDayByMonth(Long empId, LocalDate start, LocalDate end){
        Employee emp = new Employee();
        emp.setId(empId);

        List<Attendance> listAtt = attendanceRepository
                .findByEmployeeAndDayIsBetween(emp, start, end);

        return attendanceMapper.attendanceToDtoList(listAtt);
    }




    private boolean getPresentFromAttendance(Attendance attendance) {
        boolean present = false;
        if (attendance.getCheckInStatus() == AttendanceStatus.ONTIME ||
        attendance.getCheckOutStatus() == AttendanceStatus.ONTIME){
            present = true;
        }

        return  present;
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
}
