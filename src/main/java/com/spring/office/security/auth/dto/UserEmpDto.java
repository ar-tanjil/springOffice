package com.spring.office.security.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEmpDto {

    private Long id;
    private String name;
    private String username;
    private String role;
    private String enabled;

}
