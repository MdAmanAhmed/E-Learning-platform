package com.cognizant.project.elearning_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Enrollment;
import com.cognizant.project.elearning_platform.entity.Student;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

	Enrollment findByStudentIdAndCourseId(Student student,Course course);
	List<Enrollment> findByStudentId(Student studentId);
	List<Enrollment> findByCourseIdCourseId(int courseId);
	
}
