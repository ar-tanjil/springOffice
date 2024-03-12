package com.spring.office.department;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentMapper {

    private final EmployeeMapper employeeMapper;

    public Department dtoToDepartment(DepartmentDto dto){
        Department dep = new Department();
        if (dto.getId() != null) {
            dep.setId(dto.getId());
        }

        if (dto.getManagerId() != null){
            Employee manager = new Employee();
            manager.setId(dto.getManagerId());
            dep.setManager(manager);
        }

        dep.setDepartmentName(dto.getDepartmentName());
        dep.setDepartmentDesc(dto.getDepartmentDesc());
        return dep;
    }

    public DepartmentDto departmentToDto(Department dep){
        DepartmentDto dto = new DepartmentDto();
        if (dep.getId() != null) {
            dto.setId(dep.getId());
        }

        if (dep.getManager() != null){
            dto.setManager(employeeMapper.employeeToTable(dep.getManager()));
        }

        dto.setDepartmentName(dep.getDepartmentName());
        dto.setDepartmentDesc(dep.getDepartmentDesc());
        return dto;
    }

    public Department updateMapper(Department newDep, Department oldDep){

        if (newDep.getDepartmentName() != null){
            oldDep.setDepartmentName(newDep.getDepartmentName());
        }

        if (newDep.getDepartmentDesc() != null){
            oldDep.setDepartmentDesc(newDep.getDepartmentDesc());
        }
        return oldDep;
    }
}
