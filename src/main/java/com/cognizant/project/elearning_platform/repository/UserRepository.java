package com.cognizant.project.elearning_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u wHERE TYPE(u)=Student")
List<User> findStudent();
}
