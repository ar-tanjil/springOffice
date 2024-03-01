package com.spring.office.payroll.service;

import com.spring.office.employee.Employee;
import com.spring.office.payroll.domain.Attendance;
import com.spring.office.payroll.dto.AttendanceDto;
import com.spring.office.payroll.dto.AttendanceTable;
import com.spring.office.payroll.repo.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceDto giveAttendance(Long empId) {

        Employee emp = new Employee();
        emp.setId(empId);
        LocalDate day = LocalDate.now();
        Optional<Attendance> optAtt = attendanceRepository.findByEmployeeAndDay(emp, day);

        if (optAtt.isEmpty()) {
            Attendance att = new Attendance();

            att.setDay(day);
            att.setEmployee(emp);
            att.setEntryTime(LocalTime.now());

            var saveAtt = attendanceRepository.save(att);
            return new AttendanceDto(
                    saveAtt.getId(),
                    saveAtt.getDay(),
                    saveAtt.getEntryTime(),
                    saveAtt.getLeaveTime(),
                    saveAtt.getEmployee().getId()
            );
        }


        var updateAtt = optAtt.get();
        updateAtt.setLeaveTime(LocalTime.now());
        updateAtt.setPresent(true);
        attendanceRepository.save(updateAtt);

        return new AttendanceDto(
                updateAtt.getId(),
                updateAtt.getDay(),
                updateAtt.getEntryTime(),
                updateAtt.getLeaveTime(),
                updateAtt.getEmployee().getId()
        );
    }

    public List<AttendanceDto> getEmployeeAttendanceBetweenTime(Long id, LocalDate start, LocalDate end) {
        Employee emp = new Employee();
        emp.setId(id);

        List<Attendance> listAtt = attendanceRepository
                .findByEmployeeAndDayIsBetween(emp, start, end);

        return attendanceMapper.attendanceToDtoList(listAtt);
    }



    private boolean getPresentFromAttendance(Attendance attendance) {
        if (attendance.isPresent()) {
            return true;
        }
        return false;
    }


    private AttendanceTable getAttendanceTable(List<Attendance> list, String firstName) {

        AttendanceTable table = new AttendanceTable();

        List<Boolean> present = new ArrayList<>();

        for (Attendance att : list) {
            present.add(getPresentFromAttendance(att));
        }

        table.setFirstName(firstName);

        table.setPresent(present);

        return table;
    }


    public List<AttendanceTable> getAttendanceSheet(LocalDate start, LocalDate end) {

        Set<Employee> setEmp = attendanceRepository.findByPeriod(start, end);

        List<AttendanceTable> table = new ArrayList<>();

        for (Employee emp : setEmp) {
            List<Attendance> list = attendanceRepository
                    .findByEmployeeAndDayIsBetween(emp, start, end);
            table.add(getAttendanceTable(list, emp.getFirstName()));
        }

        return table;

    }
}
