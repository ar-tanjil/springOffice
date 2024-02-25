package com.spring.office.service;

import com.spring.office.domain.Application;
import com.spring.office.dto.ApplicantTableDto;
import com.spring.office.dto.ApplicationDto;
import com.spring.office.dto.EmployeeDto;
import com.spring.office.repo.ApplicationRepo;
import com.spring.office.service.mapper.ApplicationMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ApplicationService {
    private final ApplicationRepo repo;
    private final EmployeeService empService;
    private final ApplicationMapper mapper;
    private final JobService jobService;

    public ApplicationService(
            ApplicationRepo repo,
            EmployeeService empService,
            ApplicationMapper mapper,
            JobService jobService
    ) {
        this.repo = repo;
        this.empService = empService;
        this.mapper = mapper;
        this.jobService = jobService;
    }


    public ApplicationDto save(ApplicationDto dto) {
        var app = mapper.dtoToApplication(dto);
        var saveApp = repo.save(app);
        return mapper.applicationToDto(saveApp);
    }

    public List<ApplicantTableDto> getAll() {
        List<Application> allApp = repo.findAllByDeletedFalse();
        List<ApplicantTableDto> allDto = new ArrayList<>();
        allApp.forEach(app -> allDto.add(mapper.applicationToTable(app)));
        return allDto;
    }

    public ApplicationDto getById(Long id) {
        Optional<Application> optApp = repo.findById(id);
        return optApp.map(mapper::applicationToDto).orElse(null);
    }

    public boolean delete(Long id) {
        Optional<Application> optApp = repo.findById(id);
        if (optApp.isPresent()) {
            Application app = optApp.get();
            app.setDeleted(true);
            repo.save(app);
            return true;
        }
        return false;

    }

    public ApplicationDto update(Long id, ApplicationDto dto) {
        Optional<Application> optApp = repo.findById(id);
        if (optApp.isPresent()) {

            Application newApp = mapper.dtoToApplication(dto);
            Application oldApp = optApp.get();
            Application patchApp = mapper.updateMapper(newApp, oldApp);

            Application savedApp = repo.save(patchApp);
            return mapper.applicationToDto(savedApp);
        }

        Application elseSave = repo.save(mapper.dtoToApplication(dto));
        return mapper.applicationToDto(elseSave);

    }





    public EmployeeDto recruitApplicant(Long id){
        Optional<Application> optApp = repo.findByIdAndDeletedFalse(id);
        if (optApp.isPresent()){
            this.delete(optApp.get().getId());
            var empDto = mapper.applicationToEmployee(optApp.get());
            return empService.saveEmployee(empDto);
        }
        return null;
    }
}
