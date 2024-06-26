package com.spring.office.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper departmentMapper;


    public List<DepartmentDto> getAllDepartment() {
        Iterable<Department> allDep = departmentRepo.findAllByDeletedFalse();
        List<DepartmentDto> listDep = new ArrayList<>();
        allDep.forEach(department -> {
            listDep.add(departmentMapper.departmentToDto(department));
        });
        return listDep;
    }

    public DepartmentDto getDepartmentById(Long id) {
        Optional<Department> optDep = departmentRepo.findById(id);
        return optDep.map(departmentMapper::departmentToDto).orElse(null);
    }

    public DepartmentDto saveDepartment(DepartmentDto dto) {
        Department dep = departmentMapper.dtoToDepartment(dto);
        Department saveDep = departmentRepo.save(dep);
        return departmentMapper.departmentToDto(saveDep);
    }

    //    It is patch update method.
    public DepartmentDto updateDepartment(Long id, DepartmentDto dto) {
        Optional<Department> optDep = departmentRepo.findById(id);
        if (optDep.isPresent()) {
            Department newDep = departmentMapper.dtoToDepartment(dto);
            Department oldDep = optDep.get();
            Department patchDep = departmentMapper.updateMapper(newDep, oldDep);

            var saveDep = departmentRepo.save(patchDep);
            return departmentMapper.departmentToDto(saveDep);
        }

        Department dep = departmentMapper.dtoToDepartment(dto);
        Department saveDep2 = departmentRepo.save(dep);
        return departmentMapper.departmentToDto(saveDep2);
    }

    //    Hard delete
    public boolean deleteDepartmentById(Long id) {
        Optional<Department> dep = departmentRepo.findById(id);
        if (dep.isPresent()) {
            departmentRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
