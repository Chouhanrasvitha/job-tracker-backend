package com.rashvitha.jobtracker.companies;

import com.rashvitha.jobtracker.companies.dto.CompanyCreateRequest;
import com.rashvitha.jobtracker.companies.dto.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repo;

    public CompanyResponse create(UUID userId, CompanyCreateRequest req) {
        if (repo.existsByUserIdAndNameIgnoreCase(userId, req.name())) {
            throw new IllegalArgumentException("Company already exists for this user");
        }

        var now = OffsetDateTime.now();

        Company saved = repo.save(Company.builder()
                .userId(userId)
                .name(req.name().trim())
                .website(req.website())
                .location(req.location())
                .notes(req.notes())
                .createdAt(now)
                .updatedAt(now)
                .build());

        return toResponse(saved);
    }

    public List<CompanyResponse> list(UUID userId) {
        return repo.findAllByUserIdOrderByCreatedAtDesc(userId)
                .stream().map(this::toResponse).toList();
    }

    public CompanyResponse get(UUID userId, UUID companyId) {
        Company c = repo.findByIdAndUserId(companyId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Company not found"));
        return toResponse(c);
    }

    private CompanyResponse toResponse(Company c) {
        return new CompanyResponse(
                c.getId(), c.getName(), c.getWebsite(), c.getLocation(), c.getNotes(), c.getCreatedAt()
        );
    }
}
