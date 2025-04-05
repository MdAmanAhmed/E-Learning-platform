package com.cognizant.project.elearning_platform.service;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.CourseDTO;
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
	
	public CourseDTO addCourse(CourseDTO courseDTO,int instructor_id) {
		Course course=modelMapper.map(courseDTO,Course.class);
		Optional<Instructor> opt_container=instructorRepository.findById(instructor_id);
		if(!opt_container.isPresent()) {
			throw new InstructorDetailNotFound();
		}
		Instructor instructor=opt_container.get();
		course.setInstructorId(instructor);
		courseRepository.save(course);
		return modelMapper.map(course,CourseDTO.class);
	}
	
	public void deleteCourse(int courseId,int instructorId){
		Course course=courseRepository.findByCourseIdAndInstructorIdInstructorId(courseId,instructorId);
		if(course==null) {
			throw new InvalidCourse();
		}
		courseRepository.delete(course);
		//return new ResponseEntity(course,HttpStatus.OK);
	}
	
	public CourseDTO updateCourse(int instructorId,int courseId,CourseDTO courseDTO){
	Course course=courseRepository.findByCourseIdAndInstructorIdInstructorId(courseId,instructorId);
	if(course==null) {
		throw new InvalidCourse();
	}
		course.setContentURL(courseDTO.getContentURL());
		course.setTitle(courseDTO.getTitle());
		course.setDescription(courseDTO.getDescription());
		courseRepository.save(course);
	return modelMapper.map(course,CourseDTO.class);
	}
}
