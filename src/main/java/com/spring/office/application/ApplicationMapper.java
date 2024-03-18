package com.spring.office.application;

import com.spring.office.job.Job;
import com.spring.office.domain.embaded.Address;
import com.spring.office.domain.embaded.Qualification;
import com.spring.office.dto.table.ApplicantTableDto;
import com.spring.office.employee.EmployeeDto;
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


        if (dto.getId() != null) {
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

        if (dto.getJobId() != null) {
            Job job = new Job();
            job.setId(dto.getJobId());
            app.setJob(job);
        }

        return app;
    }


    public EmployeeDto applicationToEmployee(Application app){

        Address address = app.getAddress();
        if (address == null) {
            address = new Address();
        }

        Qualification qualification = app.getQualifications();
        if (qualification == null) {
            qualification = new Qualification();
        }
        EmployeeDto dto = new EmployeeDto();
        if (app.getId() != null) {
            dto.setApplicationId(app.getId());
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
        if (app.getJob() != null) {
            dto.setJobId(app.getJob().getId());
            if (app.getJob().getDepartment() != null){
                dto.setDepartmentId(app.getJob().getDepartment().getId());
            }
        }

        return dto;
    }

    public ApplicationDto applicationToDto(Application app) {



        Address address =   app.getAddress();
        if (address == null) {
            address = new Address();
        }

        Qualification qualification = app.getQualifications();
        if (qualification == null) {
            qualification = new Qualification();
        }
        ApplicationDto dto = new ApplicationDto();
        if (app.getId() != null) {
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
        if (app.getJob() != null) {
            dto.setJobId(app.getJob().getId());
            dto.setJobTitle(app.getJob().getJobTitle());
            if (app.getJob().getDepartment() != null){
                dto.setDepartmentName(app.getJob().getDepartment().getDepartmentName());
            }

        }

        dto.setReference(app.getReference());

        return dto;

    }

    public ApplicantTableDto applicationToTable(Application app) {
        Long id = app.getId();
        String name = app.getFirstName();
        String job = "";
        String dep = "";
        if (app.getJob() != null) {
            job = app.getJob().getJobTitle();
            if (app.getJob().getDepartment() != null) {
                dep = app.getJob().getDepartment().getDepartmentName();
            }
        }

        return new ApplicantTableDto(
               id, name, job, dep
        );

    }


    public Application updateMapper(Application newApp, Application oldApp) {

        Qualification patchQual = qualificationSwapMapper(newApp.getQualifications(), oldApp.getQualifications());
        Address patchAddress = addressSwapMapper(newApp.getAddress(),oldApp.getAddress());

        oldApp.setAddress(patchAddress);
        oldApp.setQualifications(patchQual);


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

        if (newApp.getJob() != null){
            oldApp.setJob(newApp.getJob());
        }

        return oldApp;
    }


    public Address addressSwapMapper(Address newAdd, Address oldAdd){
        if (newAdd.getZipCode() != null){
            oldAdd.setZipCode(newAdd.getZipCode());
        }

        if (newAdd.getRoadNo() != null){
            oldAdd.setRoadNo(newAdd.getRoadNo());
        }

        if (newAdd.getCity() != null){
            oldAdd.setCity(newAdd.getCity());
        }

        if (newAdd.getCountry() != null){
            oldAdd.setCountry(newAdd.getCountry());
        }

        return oldAdd;
    }

    public Qualification qualificationSwapMapper(Qualification newQual, Qualification oldQual) {
        if (newQual.getSsc() != null) {
            oldQual.setSsc(newQual.getSsc());
        }
        if (newQual.getSscPassingYear() != null) {
            oldQual.setSscPassingYear(newQual.getSscPassingYear());
        }

        if (newQual.getHsc() != null) {
            oldQual.setHsc(newQual.getHsc());
        }

        if (newQual.getHscPassingYear() != null) {
            oldQual.setHscPassingYear(newQual.getHscPassingYear());
        }

        if (newQual.getUndergraduate() != null) {
            oldQual.setUndergraduate(newQual.getUndergraduate());
        }

        if (newQual.getUndergraduatePassingYear() != null) {
            oldQual.setUndergraduatePassingYear(newQual.getUndergraduatePassingYear());
        }

        if (newQual.getPostgraduate() != null) {
            oldQual.setPostgraduate(newQual.getPostgraduate());
        }

        if (newQual.getPostgraduatePassingYear() != null){
            oldQual.setUndergraduatePassingYear(newQual.getUndergraduatePassingYear());
        }
        return oldQual;

    }


}
