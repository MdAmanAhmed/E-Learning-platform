package com.cognizant.project.elearning.service;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Enrollment;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.exception.AllException.AlreadyEnrolled;
import com.cognizant.project.elearning.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.CourseRepository;
import com.cognizant.project.elearning.repository.EnrollmentRepository;
import com.cognizant.project.elearning.repository.StudentRepository;

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
	public EnrollmentResponseDTO enroll(int studentId,int courseId) {
		
		Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentDetailNotFound("Student with Id "+studentId+" not found."));
		Course course=courseRepository.findById(courseId).orElseThrow(()->new InvalidCourse("Course with Id "+courseId+" not found."));
			if(!(enrollmentRepository.findByStudentIdAndCourseId(student, course)==null)) {
				throw new AlreadyEnrolled();	
				}
		Enrollment enrollment=new Enrollment();
			enrollment.setStudentId(student);
			enrollment.setCourseId(course);
			enrollment=enrollmentRepository.save(enrollment);
			EnrollmentResponseDTO enrollmentResponseDTO=new EnrollmentResponseDTO();
			enrollmentResponseDTO.setEnrollmentId(enrollment.getEnrollmentId());
			enrollmentResponseDTO.setStudentId(enrollment.getStudentId().getUserId());
			enrollmentResponseDTO.setCourseId(enrollment.getCourseId().getCourseId());
			enrollmentResponseDTO.setCourseTitle(enrollment.getCourseId().getTitle());
			enrollmentResponseDTO.setInstructorName(enrollment.getCourseId().getInstructorId().getName());
			return enrollmentResponseDTO;
	}
	
	
	
	public List<CourseResponseDTO> viewEnrolled(int studentId) {
	Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentDetailNotFound("Student with Id "+studentId+" not found."));
		List<CourseResponseDTO> courseList=new ArrayList<>();
		List<Enrollment> enrollList=enrollmentRepository.findByStudentId(student);
		CourseResponseDTO courseResponseDTO=null;
		for(Enrollment enrollment:enrollList) {
			courseResponseDTO=new CourseResponseDTO();
			courseResponseDTO.setCourseId(enrollment.getCourseId().getCourseId());
			courseResponseDTO.setTitle(enrollment.getCourseId().getTitle());
			courseResponseDTO.setDescription(enrollment.getCourseId().getDescription());
			courseResponseDTO.setContentURL(enrollment.getCourseId().getContentURL());
			courseResponseDTO.setInstructorId(enrollment.getCourseId().getInstructorId().getUserId());
			courseResponseDTO.setInstructorName(enrollment.getCourseId().getInstructorId().getName());
		courseList.add(courseResponseDTO);
		}
		
		return courseList;
	}
	
	public List<EnrollmentResponseDTO> enrolledStudents(int courseId){
		
		List<Enrollment> el=enrollmentRepository.findByCourseIdCourseId(courseId);
		List<EnrollmentResponseDTO> res=new ArrayList<>();
		el.forEach(n->res.add(modelMapper.map(n,EnrollmentResponseDTO.class)));
		return res;
	}



}
