package com.spring.office.security.auth;

import com.spring.office.security.auth.dto.AuthRequest;
import com.spring.office.security.auth.dto.AuthResponse;
import com.spring.office.security.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/office/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {

        authService.register(request);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {

        return ResponseEntity.ok(authService.authenticate(request));
    }


}
