package com.spring.office.service;

import com.spring.office.domain.Department;
import com.spring.office.domain.Employee;
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
        Iterable<Job> allJob = jobRepo.findAllByDeletedFalse();
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

    public List<JobDto> getAllByDepartment(Long id){
        Department dep = new Department();
        dep.setId(id);
        List<Job> allJob = jobRepo.findAllByDepartment(dep);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

    public List<JobDto> getAllVacancyByDepartment(Long id){
        Department dep = new Department();
        Integer vac = 0;
        dep.setId(id);
        List<Job> allJob = jobRepo.findAllByDepartmentAndVacancyIsGreaterThan(dep, vac);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

    public List<JobDto> getAllVacancy(){
        Integer vac = 0;
        List<Job> allJob = jobRepo.findAllByVacancyIsGreaterThan(vac);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

    public JobDto getById(Long id){
        Optional<Job> job = jobRepo.findById(id);
        return job.map(jobMapper::jobToDto).orElse(null);
    }


    public JobDto saveJob(JobDto dto){
        dto.setVacancy(dto.getTotalPost());
        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

    public JobDto update(Long id, JobDto dto){

        Optional<Job> optJob = jobRepo.findById(id);

        if (optJob.isPresent()){
            Job oldJob = optJob.get();
            Job mapJob = updateMapper(jobMapper.dtoToJob(dto),oldJob);
            Job saveJob = jobRepo.save(mapJob);
            return jobMapper.jobToDto(saveJob);
        }

        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

    public void reduceVacancy(Integer req, Long id){
        jobRepo.reduceVacancy(req,id);
    }


    public void addVacancy(Integer req, Long id){
        jobRepo.addVacancy(req,id);
    }



    public Boolean checkVacancy(Long id){
        int vacancy = 0;
        Optional<Job> optJob = jobRepo.findByIdAndVacancyIsGreaterThan(
                id, vacancy);
        return optJob.isPresent();
    }

    public void updateTotalPost(int add, long jobId, long depId){

        Department dep = new Department();
        dep.setId(depId);

        jobRepo.addTotalPost(add,jobId,dep);
    }

    public boolean delete(Long id){
        Optional<Job> optJob = jobRepo.findById(id);
        if (optJob.isPresent()){
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }

    private Job updateMapper(Job newJob, Job oldJob){
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
