package com.cognizant.project.elearning_platform.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.dto.CourseResponseDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
import com.cognizant.project.elearning_platform.repository.InstructorRepository;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	InstructorRepository instructorRepository;
	
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
		course.setContentURL(courseRequestDTO.getContentURL());
		course.setTitle(courseRequestDTO.getTitle());
		course.setDescription(courseRequestDTO.getDescription());
		course=courseRepository.save(course);
		CourseResponseDTO courseResponseDTO=modelMapper.map(course, CourseResponseDTO.class);
		courseResponseDTO.setInstructorId(course.getInstructorId().getUserId());
		courseResponseDTO.setInstructorName(course.getInstructorId().getName());
		
		
	return courseResponseDTO;
	
	}
	
	
	public String removeCourse(int courseId,int instructorId) {
	Course course=courseRepository.findById(courseId).orElseThrow(()->
	new InvalidCourse("Course with Id "+courseId+" not found."));
		if(course.getInstructorId().getUserId()!=instructorId) {
			return "sorry no permission for u";
		}
		courseRepository.delete(course);
		return "course removed";
		
	}
	
	public List<CourseRequestDTO> viewAllCourse(int instructorId){
		Instructor instructor=instructorRepository.findById(instructorId).orElseThrow(()->
		new InstructorDetailNotFound("Instructor with Id "+instructorId+" not found."));
		List<Course> courseList=courseRepository.findByInstructorId(instructor);
		ArrayList<CourseRequestDTO> ret=new ArrayList();
		for(Course obj:courseList) {
			ret.add(modelMapper.map(obj, CourseRequestDTO.class));
		}
		return ret;
	}

	public List<CourseRequestDTO> viewAllCourse(){
		List<Course> courseList=courseRepository.findAll();
		ArrayList<CourseRequestDTO> ret=new ArrayList();
		for(Course obj:courseList) {
			ret.add(modelMapper.map(obj, CourseRequestDTO.class));
		}
		return ret;
	}
	
}
