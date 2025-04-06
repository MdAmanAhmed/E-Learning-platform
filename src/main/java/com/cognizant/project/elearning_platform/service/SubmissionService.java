package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.SubmissionDTO;
import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.entity.Submission;
import com.cognizant.project.elearning_platform.exception.AllException.AssessmentNotFound;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.AssessmentRepository;
import com.cognizant.project.elearning_platform.repository.StudentRepository;
import com.cognizant.project.elearning_platform.repository.SubmissionRepository;

@Service
public class SubmissionService {
@Autowired
	ModelMapper modelMapper;
@Autowired
SubmissionRepository submissionRepository;
@Autowired
AssessmentRepository assessmentRepository ;
@Autowired
StudentRepository studentRepository;

	public SubmissionDTO submitAssessment(SubmissionDTO submissionDTO,int studentId,int assessmentId) {
		
	Submission submission=modelMapper.map(submissionDTO, Submission.class);
	
	Optional<Student> studentContainer=studentRepository.findById(studentId);
	Optional<Assessment> assessmentContainer=assessmentRepository.findById(assessmentId);
	
	if(!studentContainer.isPresent()) {
		throw new StudentDetailNotFound();
	}
	if(!assessmentContainer.isPresent()) {
		throw new AssessmentNotFound();
	}
	
	Student student=studentContainer.get();
	Assessment assessment=assessmentContainer.get();
	submission.setAssessmentId(assessment);
	submission.setStudentId(student);
	submissionRepository.save(submission);
	return modelMapper.map(submission, SubmissionDTO.class);
	}
}
