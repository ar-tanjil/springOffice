package com.spring.office.service;

import com.spring.office.domain.Department;
import com.spring.office.domain.Job;
import com.spring.office.dto.JobDto;
import com.spring.office.repo.JobRepo;
import com.spring.office.service.mapper.JobMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

//    Job repository
    private final JobRepo jobRepo;

//        A mapper class to convert job class to other class.
    private final JobMapper jobMapper;

//    Get all jobs from database where Deleted is false. Return JobDto list.
    public List<JobDto> getAllJobs(){
        List<Job> allJob = jobRepo.findAllByDeletedFalse();
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

//    Get all jobs by department. Return a jobDto list.
    public List<JobDto> getAllJobsByDepartment(Long id){
        Department dep = new Department();
        dep.setId(id);
        List<Job> allJob = jobRepo.findAllByDepartment(dep);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

//    Get all vacancy by department. Return a jobDto list.
    public List<JobDto> getAllVacancyByDepartment(Long id){
        Department dep = new Department();
        Integer vac = 0;
        dep.setId(id);
        List<Job> allJob = jobRepo.findAllByDepartmentAndVacancyIsGreaterThan(dep, vac);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }

//    Get all vacancy. Return a JobDto List.
    public List<JobDto> getAllVacancy(){
        Integer vac = 0;
        List<Job> allJob = jobRepo.findAllByVacancyIsGreaterThan(vac);
        List<JobDto> dtoJob = new ArrayList<>();
        allJob.forEach((job) -> dtoJob.add(jobMapper.jobToDto(job)));
        return dtoJob;
    }


//    Get job by job id, Return a JobDto.
    public JobDto getJobById(Long id){
        Optional<Job> job = jobRepo.findById(id);
        return job.map(jobMapper::jobToDto).orElse(null);
    }


//    Save job. Return A JobDto.
    public JobDto saveJob(JobDto dto){
        dto.setVacancy(dto.getTotalPost());
        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

//    Update Job by id. Return JobDto. It's complete updated.
//    Should use in a patch mapping.
    public JobDto updateJob(Long id, JobDto dto){

        Optional<Job> optJob = jobRepo.findById(id);

        if (optJob.isPresent()){
            Job oldJob = optJob.get();
            Job mapJob = jobMapper.updateMapper(jobMapper.dtoToJob(dto),oldJob);
            Job saveJob = jobRepo.save(mapJob);
            return jobMapper.jobToDto(saveJob);
        }

        Job job = jobMapper.dtoToJob(dto);
        Job saveJob = jobRepo.save(job);
        return jobMapper.jobToDto(saveJob);
    }

//    Reduce vacancy of a Job. Return void.
    public void reduceVacancy(Integer req, Long id){
        jobRepo.reduceVacancy(req,id);
    }

//      Add or Rise vacancy oa a Job. Return void.
    public void addVacancy(Integer req, Long id){
        jobRepo.addVacancy(req,id);
    }


// Check Vacancy for a Job by Job id. Return Boolean.
    public Boolean checkVacancyByJobId(Long id){
        int vacancy = 0;
        Optional<Job> optJob = jobRepo.findByIdAndVacancyIsGreaterThan(
                id, vacancy);
        return optJob.isPresent();
    }

//    Add new Post to a job. Return void.
    public void addTotalPost(int add, long jobId, long depId){

        Department dep = new Department();
        dep.setId(depId);

        jobRepo.addTotalPost(add,jobId,dep);
    }
// Delete a job by it's id. It needs further modification.
//    like nobody can delete if this job has an active employee.
//    Another modification is job needs a life cycle. when job was created
//    when the job was dismissed. and have to make it a soft delete method.
    public boolean deleteJobById(Long id){
        Optional<Job> optJob = jobRepo.findById(id);
        if (optJob.isPresent()){
            jobRepo.deleteById(id);
            return true;
        }
        return false;
    }





}
