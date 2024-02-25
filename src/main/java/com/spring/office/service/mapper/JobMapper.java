package com.spring.office.service.mapper;

import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import com.spring.office.dto.JobDto;
import org.springframework.stereotype.Service;

@Service
public class JobMapper {

//    This method accept a Job and return a JobDto
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

//  This method accept a JobDto and return a JobDto
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

//    A mapper to update Job.
    public Job updateMapper(Job newJob, Job oldJob){
        if (newJob.getId() != null){
            oldJob.setId(newJob.getId());
        }
        if (newJob.getJobTitle() != null){
            oldJob.setJobTitle(newJob.getJobTitle());
        }
        if (newJob.getDepartment() != null){
            oldJob.setDepartment(newJob.getDepartment());
        }
        if (newJob.getMaxSalary() != oldJob.getMaxSalary()){
            oldJob.setMaxSalary(newJob.getMaxSalary());
        }
        if (newJob.getMinSalary() != oldJob.getMinSalary()){
            oldJob.setMinSalary(newJob.getMinSalary());
        }

        int totalPost = oldJob.getTotalPost() + newJob.getTotalPost();
        int vacancy = oldJob.getVacancy() + newJob.getTotalPost();
        if (newJob.getTotalPost() > 0){
            oldJob.setTotalPost(totalPost);
            oldJob.setVacancy(vacancy);
        }
        return oldJob;
    }
}
