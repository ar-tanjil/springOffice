package com.spring.office.service;

import com.spring.office.domain.Job;
import com.spring.office.dto.JobDto;
import com.spring.office.repo.JobRepo;
import com.spring.office.service.mapper.JobMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepo jobRepo;
    private final JobMapper jobMapper;

    private JobService(
            JobRepo jobRepo,
            JobMapper jobMapper
    ){
        this.jobRepo = jobRepo;
        this.jobMapper = jobMapper;
    }

    public Iterable<JobDto> getAll(){
        Iterable<Job> allJob = jobRepo.findAll();
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

    public JobDto getById(Long id){
        Optional<Job> job = jobRepo.findById(id);
        return job.map(jobMapper::jobToDto).orElse(null);
    }

    public JobDto save(JobDto dto){
        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

    public JobDto update(JobDto dto){
        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

    public boolean delete(Long id){
        Optional<Job> optJob = jobRepo.findById(id);
        if (optJob.isPresent()){
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
