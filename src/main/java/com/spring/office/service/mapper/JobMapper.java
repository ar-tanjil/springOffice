package com.spring.office.service.mapper;

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

        dto.setJobTitle(job.getJobTitle());
        dto.setMinSalary(job.getMinSalary());
        dto.setMaxSalary(job.getMaxSalary());
        return dto;
    }

    public Job dtoToJob(JobDto dto) {
        Job job = new Job();

        if (dto.getId() != null) {
            job.setId(dto.getId());
        }

        job.setJobTitle(dto.getJobTitle());
        job.setMaxSalary(dto.getMaxSalary());
        job.setMinSalary(dto.getMinSalary());
        return job;
    }
}
