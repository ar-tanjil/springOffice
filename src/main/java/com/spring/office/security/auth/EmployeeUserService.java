package com.spring.office.security.auth;

import com.spring.office.employee.Employee;
import com.spring.office.employee.EmployeeDto;
import com.spring.office.employee.EmployeeService;
import com.spring.office.security.auth.dto.RegisterRequest;
import com.spring.office.security.auth.dto.UserEmpDto;
import com.spring.office.security.domain.Role;
import com.spring.office.security.domain.User;
import com.spring.office.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeUserService {

    private final EmployeeService employeeService;
    private final AuthService authService;
    private final UserRepository userRepository;

    public EmployeeDto createNewUser(EmployeeDto dto){
        var saveDto = employeeService.saveEmployee(dto);

        RegisterRequest register = RegisterRequest.builder()
                .username(saveDto.getEmail())
                .password(saveDto.getFirstName())
                .employeeId(saveDto.getId())
                .role("OTHER")
                .build();

        authService.register(register);
        return saveDto;
    }


    public List<UserEmpDto> getAllUser() {

        return userRepository.findAll()
                .stream()
                .map(this::userToDto)
                .toList();

    }


    public UserEmpDto userToDto(User user){

        UserEmpDto dto = new UserEmpDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        if (user.getEmployee() != null){
            Employee employee = user.getEmployee();
            String name = employee.getFirstName();
            if (employee.getLastName()  != null){
                name += " " + employee.getLastName();
            }
            dto.setName(name);
        }
        dto.setRole(user.getRole().toString());

        if (user.isEnabled()){
            dto.setEnabled("Active");
        } else {
            dto.setEnabled("Deactive");
        }
        return dto;

    }

    public void changerUserRole(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null){
            return;
        }
        if (user.getRole() == Role.ADMIN){
            user.setRole(Role.OTHER);
        } else {
            user.setRole(Role.ADMIN);
        }

        userRepository.save(user);

    }
}
