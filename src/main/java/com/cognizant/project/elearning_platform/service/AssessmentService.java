package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.AssessmentDTO;
import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.repository.AssessmentRepository;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
@Service
public class AssessmentService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	public AssessmentDTO createAssessment(AssessmentDTO assessmentDTO,int course_id) {
		Assessment assessment=modelMapper.map(assessmentDTO, Assessment.class);
		Optional<Course>opt_container=courseRepository.findById(course_id);
		if(!opt_container.isPresent()) {
			throw new InvalidCourse();
		}
		Course course=opt_container.get();
		assessment.setCourseId(course);
		assessmentRepository.save(assessment);
		
		return modelMapper.map(assessment,AssessmentDTO.class);
	}
}
