package com.spring.office.service.mapper;

import com.spring.office.domain.Application;
import com.spring.office.domain.Job;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.dto.ApplicationDto;
import org.springframework.stereotype.Service;

@Service
public class ApplicationMapper {


    public Application dtoToApplication(ApplicationDto dto) {


        Application app = new Application();
        Address address = new Address(
                dto.getRoadNo(),
                dto.getZipCode(),
                dto.getCity(),
                dto.getCountry()
        );

        Qualification qualification = new Qualification(
                dto.getSsc(),
                dto.getSscPassingYear(),
                dto.getHsc(),
                dto.getHscPassingYear(),
                dto.getUndergraduate(),
                dto.getUndergraduatePassingYear(),
                dto.getPostgraduate(),
                dto.getPostgraduatePassingYear()
        );



        if (dto.getId() != null){
            app.setId(dto.getId());
        }
        app.setFirstName(dto.getFirstName());
        app.setLastName(dto.getLastName());
        app.setDob(dto.getDob());
        app.setEmail(dto.getEmail());
        app.setPhoneNumber(dto.getPhoneNumber());
        app.setQualifications(qualification);
        app.setAddress(address);
        app.setReference(dto.getReference());

        if(dto.getJobId() != null){
            Job job = new Job();
            job.setId(dto.getJobId());
            app.setJob(job);
        }

        return app;
    }


    public ApplicationDto applicationToDto(Application app) {

        Address address = app.getAddress();
        if (address == null) {
            address = new Address();
        }

        Qualification qualification = app.getQualifications();
        if (qualification == null) {
            qualification = new Qualification();
        }
        ApplicationDto dto = new ApplicationDto();
        if(app.getId() != null){
            dto.setId(app.getId());
        }
        dto.setFirstName(app.getFirstName());
        dto.setLastName(app.getLastName());
        dto.setDob(app.getDob());
        dto.setEmail(app.getEmail());
        dto.setPhoneNumber(app.getPhoneNumber());
        dto.setSsc(qualification.getSsc());
        dto.setHsc(qualification.getHsc());
        dto.setPostgraduate(qualification.getPostgraduate());
        dto.setUndergraduate(qualification.getUndergraduate());
        dto.setSscPassingYear(qualification.getSscPassingYear());
        dto.setHscPassingYear(qualification.getHscPassingYear());
        dto.setUndergraduatePassingYear(qualification.getUndergraduatePassingYear());
        dto.setPostgraduatePassingYear(qualification.getPostgraduatePassingYear());
        dto.setZipCode(address.getZipCode());
        dto.setRoadNo(address.getRoadNo());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        System.out.println(app.getJob());
        if(app.getJob() != null){
            dto.setJobId(app.getJob().getId());
        }
        dto.setReference(app.getReference());

        return dto;

    }


}
