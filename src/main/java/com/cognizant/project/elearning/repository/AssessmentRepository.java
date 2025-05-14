package com.cognizant.project.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning.entity.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Integer> {
List<Assessment> findByCourseIdCourseId(int courseId);
}
