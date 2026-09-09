package com.stask.saastask.organization.controller;

import com.stask.saastask.organization.dto.CreateOrganizationRequest;
import com.stask.saastask.organization.entity.Organization;

import com.stask.saastask.organization.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organization createOrganization(
            @Valid @RequestBody CreateOrganizationRequest request) {

        return organizationService.createOrganization(request.getName());
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(
            @PathVariable Long id) {

        return organizationService.getOrganizationById(id);
    }
}