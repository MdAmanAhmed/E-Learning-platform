package com.cognizant.project.elearning_platform.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cognizant.project.elearning_platform.dto.SubmissionResponseDTO;
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

	public SubmissionResponseDTO submitAssessment(int studentId,int assessmentId) {
		
	
Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentDetailNotFound("Student with Id "+studentId+" not found."));
Assessment assessment=assessmentRepository.findById(assessmentId).orElseThrow(()->new AssessmentNotFound());
	Submission submission=new Submission();
	submission.setAssessmentId(assessment);
	submission.setStudentId(student);
	submission=submissionRepository.save(submission);
	SubmissionResponseDTO submissionResponseDTO=modelMapper.map(submission, SubmissionResponseDTO.class);
	submissionResponseDTO.setAssessmentId(submission.getAssessmentId().getAssessmentId());
	submissionResponseDTO.setStudentId(submission.getStudentId().getUserId());
	submissionResponseDTO.setType(submission.getAssessmentId().getType());
	submissionResponseDTO.setMaxScore(submission.getAssessmentId().getMaxScore());
	submissionResponseDTO.setCourseId(submission.getAssessmentId().getCourseId().getCourseId());
	submissionResponseDTO.setTitle(submission.getAssessmentId().getCourseId().getTitle());
	submissionResponseDTO.setInstructorName(submission.getAssessmentId().getCourseId().getInstructorId().getName());
	
	return submissionResponseDTO;
	}
}
