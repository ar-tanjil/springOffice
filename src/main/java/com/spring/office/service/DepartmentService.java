package com.spring.office.service;

import com.spring.office.domain.Department;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.repo.DepartmentRepo;
import com.spring.office.service.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper departmentMapper;

    private DepartmentService(
            DepartmentRepo departmentRepo,
            DepartmentMapper departmentMapper
                              ){
        this.departmentRepo = departmentRepo;
        this.departmentMapper = departmentMapper;
    }

    public List<DepartmentDto> getAll(){
        Iterable<Department> allDep = departmentRepo.findAll();
        List<DepartmentDto> listDep = new ArrayList<>();
        allDep.forEach(department -> {
            listDep.add(departmentMapper.departmentToDto(department));
        });
        return listDep;
    }

    public DepartmentDto getById(Long id){
        Optional<Department> optDep = departmentRepo.findById(id);
        return optDep.map(departmentMapper::departmentToDto).orElse(null);
    }

    public DepartmentDto save(DepartmentDto dto){
       Department dep = departmentMapper.dtoToDepartment(dto);
       Department saveDep = departmentRepo.save(dep);
       return departmentMapper.departmentToDto(saveDep);
    }

    public DepartmentDto update(DepartmentDto dto){
        Department dep = departmentMapper.dtoToDepartment(dto);
        Department saveDep = departmentRepo.save(dep);
        return departmentMapper.departmentToDto(saveDep);
    }

    public boolean delete(Long id){
        Optional<Department> dep = departmentRepo.findById(id);
        if(dep.isPresent()){
            departmentRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
