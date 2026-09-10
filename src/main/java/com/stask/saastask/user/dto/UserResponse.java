package com.stask.saastask.user.dto;

import com.stask.saastask.user.entity.Role;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private Long organizationId;

    public UserResponse() {
    }
    public UserResponse(
            Long id){
        this.id=id ;
    }
    public UserResponse(
            Long id,
            String name,
            String email,
            Role role,
            Long organizationId) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.organizationId = organizationId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}