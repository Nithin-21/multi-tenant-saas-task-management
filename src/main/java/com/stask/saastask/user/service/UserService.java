package com.stask.saastask.user.service;

import com.stask.saastask.organization.entity.Organization;
import com.stask.saastask.organization.repository.OrganizationRepository;
import com.stask.saastask.user.dto.CreateUserRequest;
import com.stask.saastask.user.dto.UserResponse;
import com.stask.saastask.user.entity.User;
import com.stask.saastask.user.repository.UserRepository;
import com.stask.saastask.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public UserService(
            UserRepository userRepository,
            OrganizationRepository organizationRepository) {

        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {

        Organization organization = organizationRepository
                .findById(request.getOrganizationId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Organization not found with id: "
                                + request.getOrganizationId()
                ));

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setOrganization(organization);

        User savedUser = userRepository.save(user);

       /* return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getOrganization().getId()
        );*/
        return new UserResponse(
                savedUser.getId()
        );



    }
}