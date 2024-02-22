package com.spring.office;


import com.spring.office.domain.Job;
import com.spring.office.dto.ApplicationDto;
import com.spring.office.dto.DepartmentDto;
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
	public CommandLineRunner dataLoad(){
		return (a) -> {
			JobDto job = new JobDto();
			job.setJobTitle("Manager");
			jobService.save(job);

			DepartmentDto dep = new DepartmentDto();
			dep.setDepartmentName("Admin");
			departmentService.save(dep);

			ApplicationDto appDto = new ApplicationDto();
			appDto.setFirstName("Ashiq");
			var saveApp = applicationService.save(appDto);
			System.out.println(saveApp);
			applicationService.delete(saveApp.getId());

		};
	}

}
