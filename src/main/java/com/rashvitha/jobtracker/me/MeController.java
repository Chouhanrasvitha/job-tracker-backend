package com.rashvitha.jobtracker.me;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class MeController {

    @GetMapping("/api/me")
    public Object me(Authentication auth) {
        return new MeResponse(
                auth.getName(),
                auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
        );
    }

    public record MeResponse(String email, java.util.List<String> roles) {}
}
