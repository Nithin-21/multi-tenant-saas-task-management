package com.stask.saastask.organization.service;

import com.stask.saastask.organization.entity.Organization;
import com.stask.saastask.organization.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization createOrganization(String name) {

        Organization organization = new Organization();
        organization.setName(name);

        return organizationRepository.save(organization);
    }

    /*public Organization getOrganizationById(Long id) {

        return organizationRepository.getById(id)
                 .orElseThrow(()-> new RuntimeException(
                "Organization not found with id: " + id )
                 );
    }*/

    public Organization getOrganizationById(Long id) {

        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Organization not found with id: " + id
                ));
    }
}