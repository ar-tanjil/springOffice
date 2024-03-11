package com.spring.office;


import com.spring.office.application.ApplicationDto;
import com.spring.office.department.DepartmentDto;
import com.spring.office.employee.EmployeeDto;
import com.spring.office.job.JobDto;
import com.spring.office.application.ApplicationService;
import com.spring.office.department.DepartmentService;
import com.spring.office.employee.EmployeeService;
import com.spring.office.job.JobService;
import com.spring.office.payroll.domain.Salary;
import com.spring.office.payroll.dto.SalaryDto;
import com.spring.office.payroll.service.OfficeDaysService;
import com.spring.office.payroll.service.SalaryService;
import com.spring.office.security.auth.AuthService;
import com.spring.office.security.auth.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OfficeDaysService officeDaysService;

    public static void main(String[] args) {
        SpringApplication.run(OfficeApplication.class, args);

        System.out.println(LocalTime.now());
    }


    	@Bean
    public CommandLineRunner dataLoad() {
        return (a) -> {



            officeDaysService.initialSave();

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

            SalaryDto salDto = SalaryDto.builder()
                    .employeeId(saveEmp.getId())
                    .basic(5000D)
                    .medicalAllowance(5D)
                    .providentFund(5D)
                    .travelAllowance(5D)
                    .loan(500D)
                    .build();
            var saveSal = salaryService.addSalary(salDto);

            var register = RegisterRequest.builder()
                    .username("ashiq")
                    .password("ashiq")
                    .role("ADMIN")
                    .employeeId(saveEmp.getId())
                    .build();


        authService.register(register);

        };
    }

}
