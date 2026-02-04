package com.rashvitha.jobtracker.companies.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyCreateRequest(
        @NotBlank @Size(max = 160) String name,
        @Size(max = 255) String website,
        @Size(max = 160) String location,
        String notes
) {}
