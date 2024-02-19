package com.spring.office.service;

import com.spring.office.customUtil.DtoUtil;
import com.spring.office.domain.Department;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private DepartmentRepo departmentRepo;

    private DepartmentService(DepartmentRepo departmentRepo){
        this.departmentRepo = departmentRepo;
    }

    public Iterable<DepartmentDto> getAll(){
        Iterable<Department> allDep = departmentRepo.findAll();
        List<DepartmentDto> listDep = new ArrayList<>();
        allDep.forEach((dep) -> DtoUtil.depToDto(dep));
        return listDep;
    }

    public DepartmentDto getById(Long id){
        Optional<Department> optDep = departmentRepo.findById(id);
        if (optDep.isPresent()){
            return DtoUtil.depToDto(optDep.get());
        }
        return null;
    }

    public DepartmentDto save(DepartmentDto dto){
       Department dep = DtoUtil.dtoToDep(dto);
       Department saveDep = departmentRepo.save(dep);
       return DtoUtil.depToDto(saveDep);
    }

    public DepartmentDto update(DepartmentDto dto){
        Department dep = DtoUtil.dtoToDep(dto);
        Department saveDep = departmentRepo.save(dep);
        return DtoUtil.depToDto(saveDep);
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
