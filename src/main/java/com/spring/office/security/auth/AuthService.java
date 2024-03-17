package com.spring.office.security.auth;

import com.spring.office.employee.Employee;
import com.spring.office.security.auth.dto.AuthRequest;
import com.spring.office.security.auth.dto.AuthResponse;
import com.spring.office.security.auth.dto.RegisterRequest;
import com.spring.office.security.domain.Role;
import com.spring.office.security.domain.User;
import com.spring.office.security.repository.UserRepository;
import com.spring.office.security.securityConfig.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void register(RegisterRequest request) {
        if (request.getEmployeeId() == null){
            return;
        }
        Role role = Role.OTHER;
        if (request.getRole() != null){
            role = Role.valueOf(request.getRole());
        }

        Employee employee = new Employee();
        employee.setId(request.getEmployeeId());

        var user =  User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .credentialsNonExpired(true)
                .employee(employee)
                .build();
        userRepository.save(user);

    }

    public AuthResponse authenticate(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        HashMap<String, Object> role = new HashMap<>();
        role.put("role", user.getRole());
        if (user.getEmployee() != null){
            Employee employee = user.getEmployee();
            role.put("id", employee.getId());

            String name = employee.getFirstName();
            if (employee.getLastName() != null){
                name += employee.getLastName();
            }

            role.put("name", name);
        }


        var jwtToken = jwtService.generateToken(role, user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();

    }


}
