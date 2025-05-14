package com.cognizant.project.elearning.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.dto.CourseRequestDTO;
import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Instructor;
import com.cognizant.project.elearning.entity.Notification;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.exception.AccessRestricted;
import com.cognizant.project.elearning.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.CourseRepository;
import com.cognizant.project.elearning.repository.InstructorRepository;
import com.cognizant.project.elearning.repository.NotificationRepository;
import com.cognizant.project.elearning.repository.StudentRepository;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	 EnrollmentService  enrollmentService;
	
	@Autowired
	InstructorRepository instructorRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	public CourseResponseDTO addCourse(CourseRequestDTO courseRequestDTO,int instructorId) {
		Course course=modelMapper.map(courseRequestDTO,Course.class);
		Instructor instructor=instructorRepository.findById(instructorId)
				.orElseThrow(()->new InstructorDetailNotFound("Instructor with Id "+instructorId+" not found."));
		course.setInstructorId(instructor);
		course=courseRepository.save(course);
		CourseResponseDTO courseResponseDTO=modelMapper.map(course, CourseResponseDTO.class);
		courseResponseDTO.setInstructorId(course.getInstructorId().getUserId());
		courseResponseDTO.setInstructorName(course.getInstructorId().getName());
		
		
		return courseResponseDTO;
	}
	
	
	public CourseResponseDTO updateCourse(int instructorId,int courseId,CourseRequestDTO courseRequestDTO){
		Course course=courseRepository.findByCourseIdAndInstructorIdUserId(courseId,instructorId);
		if(course==null) {
			throw new InvalidCourse("Course with Id "+courseId+" not found.");
		}
		Instructor instructor=instructorRepository.findById(instructorId)
				.orElseThrow(()->new InstructorDetailNotFound("Instructor with Id "+instructorId+" not found."));
		course.setContentURL(courseRequestDTO.getContentURL());
		course.setTitle(courseRequestDTO.getTitle());
		course.setDescription(courseRequestDTO.getDescription());
		course=courseRepository.save(course);
		CourseResponseDTO courseResponseDTO=modelMapper.map(course, CourseResponseDTO.class);
		courseResponseDTO.setInstructorId(course.getInstructorId().getUserId());

		
		courseResponseDTO.setInstructorName(course.getInstructorId().getName());
		Notification notification=new Notification();
		notification.setDescription("Instructor "+courseResponseDTO.getInstructorName()+" added course "+courseResponseDTO.getTitle());
		notification.setDateTime(LocalDateTime.now());
		notification.setCourseId(course);
		notification.setInstructorId(instructor);
		List<Student> students=new ArrayList<>();
		List<EnrollmentResponseDTO> el=	enrollmentService.enrolledStudents(courseId);
		for(EnrollmentResponseDTO obj:el) {
			Student student=studentRepository.findById(obj.getStudentId()).orElseThrow(()->new StudentDetailNotFound("Student with Id "+obj.getStudentId()+" not found."));
			students.add(student);
		}
		notification.setStudentId(students);
		notificationRepository.save(notification);
		return courseResponseDTO;
	}

	public String removeCourse(int courseId,int instructorId) {
		Course course=courseRepository.findById(courseId).orElseThrow(()->
						new InvalidCourse("Course with Id "+courseId+" not found."));
		if(course.getInstructorId().getUserId()!=instructorId) {
			throw new AccessRestricted();
		}
		courseRepository.delete(course);
		return "course removed";
	}
	
	public List<CourseResponseDTO> viewAllCourse(int instructorId){
		Instructor instructor=instructorRepository.findById(instructorId).orElseThrow(()->
							new InstructorDetailNotFound("Instructor with Id "+instructorId+" not found."));
		List<Course> courseList=courseRepository.findByInstructorId(instructor);
		ArrayList<CourseResponseDTO> result=new ArrayList<>();
		for(Course obj:courseList) {
			result.add(modelMapper.map(obj, CourseResponseDTO.class));
		}
		return result;
	}

	public List<CourseResponseDTO> viewAllCourse(){
		List<Course> courseList=courseRepository.findAll();
		ArrayList<CourseResponseDTO> result=new ArrayList<>();
		for(Course obj:courseList) {
			result.add(modelMapper.map(obj, CourseResponseDTO.class));
		}
		return result;
	}
	
}
