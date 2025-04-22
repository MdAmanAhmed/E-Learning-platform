package com.cognizant.project.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Instructor;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	public Course findByCourseIdAndInstructorIdUserId(int courseId,int instructorId);
	
	List<Course> findByInstructorId(Instructor instructor );
}
