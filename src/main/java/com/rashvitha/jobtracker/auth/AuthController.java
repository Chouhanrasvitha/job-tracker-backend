package com.rashvitha.jobtracker.auth;

import com.rashvitha.jobtracker.auth.dto.AuthResponses;
import com.rashvitha.jobtracker.auth.dto.LoginRequest;
import com.rashvitha.jobtracker.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public AuthResponses register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public AuthResponses login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
