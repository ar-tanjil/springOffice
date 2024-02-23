package com.spring.office.service;

import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import com.spring.office.dto.DepartReceiveDto;
import com.spring.office.repo.DepartmentRepo;
import com.spring.office.service.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<DepartReceiveDto> getAll(){
        Iterable<Department> allDep = departmentRepo.findAll();
        List<DepartReceiveDto> listDep = new ArrayList<>();
        allDep.forEach(department -> {
            listDep.add(departmentMapper.departmentToDto(department));
        });
        return listDep;
    }

    public DepartReceiveDto getById(Long id){
        Optional<Department> optDep = departmentRepo.findById(id);
        return optDep.map(departmentMapper::departmentToDto).orElse(null);
    }

    public DepartReceiveDto save(DepartReceiveDto dto){
       Department dep = departmentMapper.dtoToDepartment(dto);
       Department saveDep = departmentRepo.save(dep);
       return departmentMapper.departmentToDto(saveDep);
    }

    public DepartReceiveDto update(Long id, DepartReceiveDto dto){
        Department newDep = departmentMapper.dtoToDepartment(dto);
        Optional<Department> optDep = departmentRepo.findById(id);
        if (optDep.isPresent()){
            Department oldDep = optDep.get();

            if (newDep.getDepartmentName() != null){
                oldDep.setDepartmentName(newDep.getDepartmentName());
            }
            if (newDep.getDepartmentDesc() != null){
                oldDep.setDepartmentDesc(newDep.getDepartmentDesc());
            }
            if (newDep.getJob() != null){
                Set<Job> job = new HashSet<>();
                if (oldDep.getJob() != null){
                    job = oldDep.getJob();
                }
                job.addAll(newDep.getJob());
            }

          var saveDep = departmentRepo.save(oldDep);
            return departmentMapper.departmentToDto(saveDep);
        }

        Department dep = departmentMapper.dtoToDepartment(dto);
        Department saveDep2 = departmentRepo.save(dep);
        return departmentMapper.departmentToDto(saveDep2);
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
