package com.rashvitha.jobtracker.companies;

import com.rashvitha.jobtracker.companies.dto.CompanyCreateRequest;
import com.rashvitha.jobtracker.companies.dto.CompanyResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    private UUID currentUserId(Authentication auth) {
        return UUID.fromString(auth.getName()); // because we set principal=userId in filter
    }

    @PostMapping
    public CompanyResponse create(Authentication auth, @Valid @RequestBody CompanyCreateRequest req) {
        return service.create(currentUserId(auth), req);
    }

    @GetMapping
    public List<CompanyResponse> list(Authentication auth) {
        return service.list(currentUserId(auth));
    }

    @GetMapping("/{id}")
    public CompanyResponse get(Authentication auth, @PathVariable UUID id) {
        return service.get(currentUserId(auth), id);
    }
}
