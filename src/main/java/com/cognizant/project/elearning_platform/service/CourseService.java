package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.CourseDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
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
		if(opt_container.get()==null) {
			
		}
		Instructor instructor=opt_container.get();
		course.setInstructorId(instructor);
		courseRepository.save(course);
		return modelMapper.map(course,CourseDTO.class);
	}
}
