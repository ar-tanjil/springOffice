package com.spring.office.security.auth.dto;

import com.spring.office.security.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String username;
    private String password;
    private String role;
    private Long employeeId;

}
