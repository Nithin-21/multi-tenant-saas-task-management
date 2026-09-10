package com.stask.saastask.organization.service;

import com.stask.saastask.common.exception.ResourceNotFoundException;
import com.stask.saastask.organization.dto.OrganizationResponse;
import com.stask.saastask.organization.entity.Organization;
import com.stask.saastask.organization.repository.OrganizationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization createOrganization(String name) {
        /*if(name.isBlank()){
         throw  new nullPointerException  ("Please provide the Organization name");
        }*/
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

    public OrganizationResponse getOrganizationById(Long id) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organization not found with id: " + id
                ));
        return new OrganizationResponse(
                organization.getId(),
                organization.getName() ) ;
    }

    public Page<OrganizationResponse> getAllOrganizations(Pageable pageable) {

        return organizationRepository.findAll(pageable)
                .map(organization -> new OrganizationResponse(
                        organization.getId(),
                        organization.getName()
                ));
    }

    public OrganizationResponse updateOrganization( Long id,String name) {

        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organization not found with id: " + id
                ));

        organization.setName(name);

        Organization updatedOrganization =
                organizationRepository.save(organization);

        return new OrganizationResponse(
                updatedOrganization.getId(),
                updatedOrganization.getName()
        );
    }
}