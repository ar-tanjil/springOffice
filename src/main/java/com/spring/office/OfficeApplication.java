package com.spring.office;


import com.spring.office.dto.DepartmentDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.dto.JobDto;
import com.spring.office.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OfficeApplication {

	@Autowired
	private EmployeeService service;

	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoad(){
		return (a) -> {
			EmployeeDto dtoEmp = new EmployeeDto();
			dtoEmp.setFirstName("Ashiq");
			dtoEmp.setLastName("Rahman");
			dtoEmp.setEmail("artanjil@gmail.com");
			dtoEmp.setPhoneNumber("0147585");
			dtoEmp.setHireDate(LocalDate.now());

			JobDto job = new JobDto();
			job.setJobTitle("manager");
			job.setMaxSalary(20000);
			job.setMinSalary(10000);

			DepartmentDto dep = new DepartmentDto();
			dep.setManagerId(1l);
			dep.setDepartmentName("Admin");
			dep.setDepartmentDesc("Chocke");

			dtoEmp.setJob(job);
			dtoEmp.setDepartment(dep);
			service.save(dtoEmp);

		};
	}

}
