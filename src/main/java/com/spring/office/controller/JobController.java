package com.spring.office.controller;

import com.spring.office.dto.JobDto;
import com.spring.office.dto.Message;
import com.spring.office.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/designations")
@CrossOrigin(origins = "http://localhost:4200")
public class JobController {

    private final JobService service;

    public JobController(
            JobService service
    ) {
        this.service = service;
    }


    @GetMapping
    public Iterable<JobDto> getAll() {
        return service.getAllJobs();
    }

    @GetMapping("/departments/{id}")
    public Iterable<JobDto> getAllByDepartment(@PathVariable("id") Long id){
        return service.getAllJobsByDepartment(id);
    }

    @PutMapping("/vacancy/{job_id}/{recruit}")
    public void updateVacancy(
            @PathVariable("job_id") Long jobId,
            @PathVariable("recruit") int recruit
    ){
        service.reduceVacancy(recruit, jobId);
    }

    @GetMapping("check_vacancy/{id}")
    public boolean checkVacancy(
            @PathVariable("id") Long id
    ){
        return service.checkVacancyByJobId(id);
    }

    @PutMapping("total_post/{job_id}/{dep_id}/{add}")
    public void updateTotalPos(
            @PathVariable("job_id") Long jobId,
            @PathVariable("dep_id") Long depId,
            @PathVariable("add") int add
    ){
        service.addTotalPost(add,jobId,depId);
    }

    @GetMapping("/departments/vacancy/{id}")
    public Iterable<JobDto> getAllVacancyByDepartment(@PathVariable("id") Long id){
        return service.getAllVacancyByDepartment(id);
    }

    @GetMapping("/{id}")
    public JobDto getById(@PathVariable("id") Long id) {
        return service.getJobById(id);
    }

    @PostMapping
    public JobDto saveEmployee(@RequestBody JobDto dto) {
        return service.saveJob(dto);
    }

    @PutMapping("/{id}")
    public JobDto updateEmployee(@PathVariable("id") Long id,
                                 @RequestBody JobDto dto) {
        return service.updateJob(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteById(@PathVariable("id") Long id) {
        boolean success = service.deleteJobById(id);
        if (success) {
            Message successMsg = new Message("Success");
            return new ResponseEntity<>(successMsg, HttpStatus.ACCEPTED);
        }
        Message failedMsg = new Message("Not Found");
        return new ResponseEntity<>(failedMsg, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/vacancy")
    public Iterable<JobDto> getAllByVacancy(){
        return service.getAllVacancy();
    }

}
