package com.rashvitha.jobtracker.companies.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        String website,
        String location,
        String notes,
        OffsetDateTime createdAt
) {}
