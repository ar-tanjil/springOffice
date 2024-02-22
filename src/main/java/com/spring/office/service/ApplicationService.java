package com.spring.office.service;

import com.spring.office.domain.Application;
import com.spring.office.dto.ApplicationDto;
import com.spring.office.repo.ApplicationRepo;
import com.spring.office.service.mapper.ApplicationMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    private final ApplicationRepo repo;
    private final ApplicationMapper mapper;

    public ApplicationService(
            ApplicationRepo repo,
            ApplicationMapper mapper
    ) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public ApplicationDto save(ApplicationDto dto) {
        var app = mapper.dtoToApplication(dto);
        var saveApp = repo.save(app);
        return mapper.applicationToDto(saveApp);

    }

    public List<ApplicationDto> getAll() {
        List<Application> allApp = repo.findAllByDeletedFalse();
        List<ApplicationDto> allDto = new ArrayList<>();
        allApp.forEach(app -> allDto.add(mapper.applicationToDto(app)));
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
            ApplicationDto oldDto = mapper.applicationToDto(optApp.get());
            ApplicationDto updatedDto = updateMapper(oldDto, dto);
            Application updatedApp = mapper.dtoToApplication(updatedDto);
            Application savedApp = repo.save(updatedApp);
            return mapper.applicationToDto(savedApp);
        }

        Application elseSave = repo.save(mapper.dtoToApplication(dto));
        return mapper.applicationToDto(elseSave);

    }


    private ApplicationDto updateMapper(ApplicationDto oldApp, ApplicationDto newApp) {
        if (newApp.getFirstName() != null) {
            oldApp.setFirstName(newApp.getFirstName());
        }
        if (newApp.getLastName() != null) {
            oldApp.setLastName(newApp.getLastName());
        }
        if (newApp.getEmail() != null) {
            oldApp.setEmail(newApp.getEmail());
        }
        if (newApp.getPhoneNumber() != null) {
            oldApp.setPhoneNumber(newApp.getPhoneNumber());
        }
        if (newApp.getReference() != null) {
            oldApp.setReference(newApp.getReference());
        }
        if (newApp.getDob() != null) {
            oldApp.setDob(newApp.getDob());
        }
        if (newApp.getSsc() != null) {
            oldApp.setSsc(newApp.getSsc());
        }
        if (newApp.getHsc() != null) {
            oldApp.setHsc(newApp.getHsc());
        }
        if (newApp.getUndergraduate() != null) {
            oldApp.setUndergraduate(newApp.getUndergraduate());
        }
        if (newApp.getPostgraduate() != null) {
            oldApp.setPostgraduate(newApp.getPostgraduate());
        }
        if (newApp.getSscPassingYear() != null) {
            oldApp.setSscPassingYear(newApp.getSscPassingYear());
        }
        if (newApp.getHscPassingYear() != null) {
            oldApp.setHscPassingYear(newApp.getHscPassingYear());
        }
        if (newApp.getUndergraduatePassingYear() != null) {
            oldApp.setUndergraduatePassingYear(newApp.getUndergraduatePassingYear());
        }
        if (newApp.getPostgraduatePassingYear() != null) {
            oldApp.setPostgraduatePassingYear(newApp.getPostgraduatePassingYear());
        }
        if (newApp.getZipCode() != null) {
            oldApp.setZipCode(newApp.getZipCode());
        }
        if (newApp.getRoadNo() != null) {
            oldApp.setRoadNo(newApp.getRoadNo());
        }

        if (newApp.getCity() != null) {
            oldApp.setCity(newApp.getCity());
        }
        if (newApp.getCountry() != null) {
            oldApp.setCountry(newApp.getCountry());
        }
        return oldApp;
    }

}
