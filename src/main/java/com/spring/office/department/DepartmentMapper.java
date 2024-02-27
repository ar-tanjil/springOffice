package com.spring.office.department;

import org.springframework.stereotype.Service;

@Service
public class DepartmentMapper {

    public Department dtoToDepartment(DepartmentDto dto){
        Department dep = new Department();
        if (dto.getId() != null) {
            dep.setId(dto.getId());
        }

        dep.setDepartmentName(dto.getDepartmentName());
        dep.setDepartmentDesc(dto.getDepartmentDesc());
        dep.setManagerId(dto.getManagerId());
        return dep;
    }

    public DepartmentDto departmentToDto(Department dep){
        DepartmentDto dto = new DepartmentDto();
        if (dep.getId() != null) {
            dto.setId(dep.getId());
        }
        dto.setDepartmentName(dep.getDepartmentName());
        dto.setDepartmentDesc(dep.getDepartmentDesc());
        dto.setManagerId(dep.getManagerId());
        return dto;
    }

    public Department updateMapper(Department newDep, Department oldDep){

        if (newDep.getDepartmentName() != null){
            oldDep.setDepartmentName(newDep.getDepartmentName());
        }

        if (newDep.getDepartmentName() != null){
            oldDep.setDepartmentName(newDep.getDepartmentDesc());
        }
        return oldDep;
    }
}
