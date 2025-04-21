package com.cognizant.project.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.project.elearning.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

}
