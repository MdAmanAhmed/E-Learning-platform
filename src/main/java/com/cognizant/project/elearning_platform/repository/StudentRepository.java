package com.cognizant.project.elearning_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning_platform.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
