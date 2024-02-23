package com.spring.office.service.mapper;

import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import com.spring.office.dto.DepartReceiveDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartmentMapper {

    public Department dtoToDepartment(DepartReceiveDto dto){
        Department dep = new Department();
        if (dto.getId() != null) {
            dep.setId(dto.getId());
        }

        dep.setDepartmentName(dto.getDepartmentName());
        dep.setDepartmentDesc(dto.getDepartmentDesc());
//        dep.setManagerId(dto.getManagerId());
        return dep;
    }

    public DepartReceiveDto departmentToDto(Department dep){
        DepartReceiveDto dto = new DepartReceiveDto();
        if (dep.getId() != null) {
            dto.setId(dep.getId());
        }
        dto.setDepartmentName(dep.getDepartmentName());
        dto.setDepartmentDesc(dep.getDepartmentDesc());
//        dto.setManagerId(dep.getManagerId());
        return dto;
    }
}
