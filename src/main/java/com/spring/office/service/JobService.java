package com.spring.office.service;

import com.spring.office.customUtil.DtoUtil;
import com.spring.office.domain.Job;
import com.spring.office.dto.JobDto;
import com.spring.office.repo.JobRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private JobRepo jobRepo;

    private JobService(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }

    public Iterable<JobDto> getAll(){
        Iterable<Job> allJob = jobRepo.findAll();
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(DtoUtil.jobToDto(job)));
        return dtoJob;
    }

    public JobDto getById(Long id){
        Optional<Job> job = jobRepo.findById(id);
        if (job.isPresent()){
            return DtoUtil.jobToDto(job.get());
        }
        return null;
    }

    public JobDto save(JobDto dto){
        Job job = DtoUtil.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return DtoUtil.jobToDto(saveJob);
    }

    public JobDto update(JobDto dto){
        Job job = DtoUtil.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return DtoUtil.jobToDto(saveJob);
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
