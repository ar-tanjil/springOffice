package com.spring.office.security.auth;

import com.spring.office.employee.EmployeeDto;
import com.spring.office.employee.EmployeeService;
import com.spring.office.security.auth.dto.RegisterRequest;
import com.spring.office.security.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class EmployeeUserService {

    private final EmployeeService employeeService;
    private final AuthService authService;

    public EmployeeDto createNewUser(EmployeeDto dto){
        var saveDto = employeeService.saveEmployee(dto);



        RegisterRequest register = RegisterRequest.builder()
                .username(saveDto.getEmail())
                .password(saveDto.getFirstName())
                .role("OTHER")
                .build();

        authService.register(register);
        return saveDto;
    }




}
