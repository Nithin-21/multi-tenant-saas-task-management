package com.stask.saastask.user.repository;

import com.stask.saastask.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}