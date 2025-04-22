package com.cognizant.project.elearning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
}
