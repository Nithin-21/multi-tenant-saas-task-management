package com.stask.saastask.organization.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateOrganizationRequest {

    @NotBlank(message = "Organization name is required")
    private String name;

    public CreateOrganizationRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}