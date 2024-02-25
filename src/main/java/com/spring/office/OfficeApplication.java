package com.spring.office;


import com.spring.office.dto.ApplicationDto;
import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
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

import java.time.LocalDate;

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

            DepartmentDto dep = new DepartmentDto();
            dep.setDepartmentName("Administration");
            dep.setDepartmentDesc("Administration Department");
            var saveDep = departmentService.saveDepartment(dep);

            JobDto job = new JobDto();
            job.setJobTitle("Manager");
            job.setTotalPost(10);
            job.setMaxSalary(20000);
            job.setMinSalary(10000);
            job.setDepartmentId(saveDep.getId());
            var saveJob = jobService.saveJob(job);


            ApplicationDto app = new ApplicationDto();
            app.setFirstName("Ashiqur");
            app.setLastName("Rahman");
            app.setDob(LocalDate.of(1997, 7, 6));
            app.setEmail("ar@gmail.com");
            app.setPhoneNumber("0172069000");
            app.setSsc("5.00");
            app.setJobId(saveJob.getId());
            app.setSscPassingYear(LocalDate.of(2012,1,1));
            app.setHsc("5.00");
            app.setHscPassingYear(LocalDate.of(2014,1,1));
            app.setUndergraduate("3.00");
            app.setUndergraduatePassingYear(LocalDate.of(2020,1,1));
            app.setZipCode("1400");
            app.setRoadNo("12, Gashroad");
            app.setCity("Dhaka");
            app.setCountry("Bangladesh");
            var saveApp = applicationService.save(app);

            var emp = new EmployeeDto();

            emp.setFirstName("Tanjil");
            emp.setLastName("Bin Moin");
            emp.setDob(LocalDate.of(1997, 7, 6));
            emp.setEmail("ar@gmail.com");
            emp.setPhoneNumber("0172069000");
            emp.setSsc("5.00");
            emp.setJobId(saveJob.getId());
            emp.setSscPassingYear(LocalDate.of(2012,1,1));
            emp.setHsc("5.00");
            emp.setHscPassingYear(LocalDate.of(2014,1,1));
            emp.setUndergraduate("3.00");
            emp.setUndergraduatePassingYear(LocalDate.of(2020,1,1));
            emp.setZipCode("1400");
            emp.setRoadNo("12, Gashroad");
            emp.setCity("Dhaka");
            emp.setCountry("Bangladesh");
            emp.setDepartmentId(saveDep.getId());

            var saveEmp = employeeService.saveEmployee(emp);

        };
    }

}
