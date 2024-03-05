package com.spring.office.chart.service;

import com.spring.office.chart.domain.DepartmentChart;
import com.spring.office.department.Department;
import com.spring.office.department.DepartmentDto;
import com.spring.office.department.DepartmentRepo;
import com.spring.office.department.DepartmentService;
import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentChartService {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;
    public List<DepartmentChart> processDepartmentChart(){
        List<Department> listDip = departmentRepo.findAllByDeletedFalse();
        List<Employee> empList = employeeRepo.findAllByDeletedFalse();

        int totalDip = listDip.size();
        int totalEmp = empList.size();

        if (listDip.isEmpty() || empList.isEmpty()){
            DepartmentChart chart = DepartmentChart.builder()
                    .name("none")
                    .y(100D)
                    .build();
            return List.of(chart);
        }

        List<DepartmentChart> depChart = new ArrayList<>();

        for (Department dep : listDip){
            DepartmentChart chart = new DepartmentChart();
            String name = dep.getDepartmentName();
            int empSizeByDep = employeeRepo.countByDepartmentAndDeletedFalse(dep);
            double y = 0;
            if (empSizeByDep > 0){
               y = (empSizeByDep / totalEmp) * 100;
            }
            chart.setName(name);
            chart.setY(y);
            depChart.add(chart);
        }

    return depChart;
    }


}
