package com.spring.office;


import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.JobDto;
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

	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoad(){
		return (a) -> {
			EmployeeDto dtoEmp = new EmployeeDto();
			dtoEmp.setId(1l);
			dtoEmp.setFirstName("Ashiq");
			dtoEmp.setLastName("Rahman");
			dtoEmp.setEmail("artanjil@gmail.com");
			dtoEmp.setPhoneNumber("0147585");
			dtoEmp.setHireDate(LocalDate.now());
			dtoEmp.setAddress("Dhaka");

			JobDto job = new JobDto();
			job.setId(1l);
			job.setJobTitle("manager");
			job.setMaxSalary(20000);
			job.setMinSalary(10000);

			DepartmentDto dep = new DepartmentDto();
			dep.setId(1l);
			dep.setManagerId(1l);
			dep.setDepartmentName("Admin");
			dep.setDepartmentDesc("Chocke");
			jobService.save(job);
			departmentService.save(dep);

			JobDto dtoJob = jobService.getById(1l);
			DepartmentDto dtoDep = departmentService.getById(1l);
			dtoEmp.setJob(dtoJob);
			dtoEmp.setDepartment(dtoDep);

			employeeService.save(dtoEmp);

		};
	}

}
