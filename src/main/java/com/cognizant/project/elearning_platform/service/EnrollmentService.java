package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.EnrollmentDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Enrollment;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.AlreadyEnrolled;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
import com.cognizant.project.elearning_platform.repository.EnrollmentRepository;
import com.cognizant.project.elearning_platform.repository.StudentRepository;

@Service
public class EnrollmentService {
@Autowired
	EnrollmentRepository enrollmentRepository ;
@Autowired
StudentRepository studentRepository;
@Autowired
CourseRepository courseRepository;
@Autowired
ModelMapper modelMapper;
public EnrollmentDTO enroll(int studentId,int courseId,EnrollmentDTO enrollmentDTO) {
	
	Enrollment enrollment=modelMapper.map(enrollmentDTO, Enrollment.class);
Optional<Student> studentContainer=studentRepository.findById(studentId);
Optional<Course> courseContainer=courseRepository.findById(courseId);
if(!studentContainer.isPresent()) {
	throw new StudentDetailNotFound();
}
	if(!courseContainer.isPresent()) {
		throw new InvalidCourse();
	}
	Student student=studentContainer.get();
	Course course=courseContainer.get();
	if(!(enrollmentRepository.findByStudentIdAndCourseId(student, course)==null)) {
		throw new AlreadyEnrolled();	
		}
	enrollment.setStudentId(student);
	enrollment.setCourseId(course);
	enrollmentRepository.save(enrollment);
	return modelMapper.map(enrollment, EnrollmentDTO.class);
}
}
