package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeRepo;
import com.spring.office.payroll.domain.Attendance;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceTable;
import com.spring.office.payroll.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final EmployeeRepo employeeRepo;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final HolidayService holidayService;



    public AttendanceDto giveAttendance(Long empId) {

        Employee emp = new Employee();
        emp.setId(empId);
        LocalDate day = LocalDate.now();


        if (holidayService.checkHoliday(day)){
            return null;
        }

        Optional<Attendance> optAtt = attendanceRepository.findByEmployeeAndDay(emp, day);

        if (optAtt.isEmpty()) {
            Attendance att = new Attendance();

            att.setDay(day);
            att.setEmployee(emp);
            att.setCheckIn(LocalTime.now());

            var saveAtt = attendanceRepository.save(att);
            return attendanceMapper.attendanceToDto(saveAtt);
        }


        var updateAtt = optAtt.get();
        updateAtt.setCheckOut(LocalTime.now());
        updateAtt.setPresent(true);
        attendanceRepository.save(updateAtt);

        return attendanceMapper.attendanceToDto(updateAtt);
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
                .findByEmployeeAndDayIsBetweenAndPresentTrue(emp, start, end);

        return attendanceMapper.attendanceToDtoList(listAtt);
    }




    private boolean getPresentFromAttendance(Attendance attendance) {
        return attendance.isPresent();
    }


    private AttendanceTable getAttendanceTable(List<Attendance> list, String firstName) {

        AttendanceTable table = new AttendanceTable();


        int size = LocalDate.now().getDayOfMonth();

        boolean[] present = new boolean[size];


        for (Attendance att : list) {

            int index = att.getDay().getDayOfMonth() - 1;

            present[index] = getPresentFromAttendance(att);

        }

        table.setFirstName(firstName);

        table.setPresent(present);

        return table;
    }


    public List<AttendanceTable> getAttendanceSheet(LocalDate start, LocalDate end) {

        List<Long> empId = employeeRepo.findAllEmployeeId();

        List<AttendanceTable> table = new ArrayList<>();

        for (Long id : empId) {
            Employee emp = new Employee();
            emp.setId(id);
            List<Attendance> list = attendanceRepository
                    .findByEmployeeAndDayIsBetween(emp, start, end);

            String firstName = employeeRepo.findFirstName(id);

            table.add(getAttendanceTable(list, firstName));
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
}
