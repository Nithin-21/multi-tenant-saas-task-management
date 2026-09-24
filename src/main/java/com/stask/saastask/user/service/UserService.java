package com.stask.saastask.user.service;

import com.stask.saastask.common.exception.ResourceNotFoundException;
import com.stask.saastask.organization.entity.Organization;
import com.stask.saastask.organization.repository.OrganizationRepository;
import com.stask.saastask.user.dto.CreateUserRequest;
import com.stask.saastask.user.dto.UpdateUserRequest;
import com.stask.saastask.user.dto.UserResponse;
import com.stask.saastask.user.entity.User;
import com.stask.saastask.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(
            UserRepository userRepository,
            OrganizationRepository organizationRepository,
              PasswordEncoder passwordEncoder) {

            this.userRepository = userRepository;
            this.organizationRepository = organizationRepository;
            this.passwordEncoder = passwordEncoder;
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
     //   user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()) );
        user.setRole(request.getRole());
        user.setOrganization(organization);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id
                ));

        return mapToResponse(user);
    }

    public Page<UserResponse> getAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public UserResponse updateUser(
            Long id,
            UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id
                ));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id
                ));

        userRepository.delete(user);
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getOrganization().getId()
        );
    }
}