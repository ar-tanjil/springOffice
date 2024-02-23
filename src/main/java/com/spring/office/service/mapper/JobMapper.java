package com.spring.office.service.mapper;

import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import com.spring.office.dto.JobDto;
import org.springframework.stereotype.Service;

@Service
public class JobMapper {

    public JobDto jobToDto(Job job) {
        JobDto dto = new JobDto();

        if (job.getId() != null) {
            dto.setId(job.getId());
        }

        if (job.getDepartment() != null){
            dto.setDepartmentId(job.getDepartment().getId());
            dto.setDepartmentName(job.getDepartment().getDepartmentName());
        }

        dto.setJobTitle(job.getJobTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        dto.setTotalPost(job.getTotalPost());
        dto.setVacancy(job.getVacancy());
        return dto;
    }

    public Job dtoToJob(JobDto dto) {
        Job job = new Job();

        if (dto.getId() != null) {
            job.setId(dto.getId());
        }

        if (dto.getDepartmentId() != null){
            Department dep = new Department();
            dep.setId(dto.getDepartmentId());
            job.setDepartment(dep);
        }

        job.setJobTitle(dto.getJobTitle());
        job.setMaxSalary(dto.getMaxSalary());
        job.setMinSalary(dto.getMinSalary());
        job.setTotalPost(dto.getTotalPost());
        job.setVacancy(dto.getVacancy());
        return job;
    }
}
