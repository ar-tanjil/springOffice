package com.spring.office;


import com.spring.office.dto.ApplicationDto;
import com.spring.office.dto.DepartReceiveDto;
import com.spring.office.dto.JobDto;
import com.spring.office.service.ApplicationService;
import com.spring.office.service.DepartmentService;
import com.spring.office.service.EmployeeService;
import com.spring.office.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OfficeApplication {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobService jobService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ApplicationService applicationService;

    public static void main(String[] args) {
        SpringApplication.run(OfficeApplication.class, args);
    }


    	@Bean
    public CommandLineRunner dataLoad() {
        return (a) -> {

            DepartReceiveDto dep = new DepartReceiveDto();
            dep.setDepartmentName("Administration");
            dep.setDepartmentDesc("Administration Department");
            var saveDep = departmentService.save(dep);

            JobDto job = new JobDto();
            job.setJobTitle("Manager");
            job.setTotalPost(10);
            job.setMaxSalary(20000);
            job.setMinSalary(10000);
            job.setDepartmentId(saveDep.getId());
            var saveJob = jobService.saveJob(job);


            ApplicationDto appDto = new ApplicationDto();
            appDto.setFirstName("Ashiq");
            appDto.setJobId(saveJob.getId());
            var saveApp = applicationService.save(appDto);


        };
    }

}
