package com.cognizant.project.elearning.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.dto.SubmissionRequestDTO;
import com.cognizant.project.elearning.dto.SubmissionResponseDTO;
import com.cognizant.project.elearning.entity.Assessment;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.entity.Submission;
import com.cognizant.project.elearning.exception.AllException.AssessmentNotFound;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.AssessmentRepository;
import com.cognizant.project.elearning.repository.StudentRepository;
import com.cognizant.project.elearning.repository.SubmissionRepository;

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
	
	public SubmissionResponseDTO submitAssessment(int studentId,int assessmentId, SubmissionRequestDTO submissionDTO) {
		Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentDetailNotFound("Student with Id "+studentId+" not found."));
		Assessment assessment=assessmentRepository.findById(assessmentId).orElseThrow(()->new AssessmentNotFound());
		Submission submission=new Submission();
		submission.setAssessmentId(assessment);
		submission.setStudentId(student);
		submission.setAnswer(submissionDTO.getAnswer());
		assessmentRepository.save(assessment);
		submission=submissionRepository.save(submission);
//		SubmissionResponseDTO submissionResponseDTO=modelMapper.map(submission, SubmissionResponseDTO.class);
		SubmissionResponseDTO submissionResponseDTO=new SubmissionResponseDTO();
		submissionResponseDTO.setAssessmentId(assessmentId);
		submissionResponseDTO.setQuestion(assessment.getQuestion());
		submissionResponseDTO.setAnswer(submissionDTO.getAnswer());
		submissionResponseDTO.setMaxScore(assessment.getMaxScore());
		submissionResponseDTO.setSubmissionId(submission.getSubmissionId());
		submissionResponseDTO.setTitle(assessment.getCourseId().getTitle());
		submissionResponseDTO.setStudentId(studentId);
		return submissionResponseDTO;
	}

	public SubmissionResponseDTO viewAnswer(int assessmentId,int submissionId) {
		Submission submission=submissionRepository.findById(submissionId).get();
		Assessment assessment=assessmentRepository.findById(assessmentId).get();
		SubmissionResponseDTO submissionResponseDTO=new SubmissionResponseDTO();
		submissionResponseDTO.setAssessmentId(assessmentId);
		submissionResponseDTO.setQuestion(assessment.getQuestion());
		submissionResponseDTO.setAnswer(submission.getAnswer());
		submissionResponseDTO.setMaxScore(assessment.getMaxScore());
		submissionResponseDTO.setSubmissionId(submission.getSubmissionId());
		submissionResponseDTO.setTitle(assessment.getCourseId().getTitle());

		
		return submissionResponseDTO;
	}

	public SubmissionResponseDTO viewScore(int submissionId) {
		Submission submission = submissionRepository.findById(submissionId).get();
		Assessment assessment = assessmentRepository.findById(submission.getAssessmentId().getAssessmentId()).get();
		SubmissionResponseDTO submissionResponseDTO=modelMapper.map(submission,SubmissionResponseDTO.class);
		submissionResponseDTO.setQuestion(assessment.getQuestion());
		submissionResponseDTO.setAssessmentId(assessment.getAssessmentId());
		submissionResponseDTO.setMaxScore(assessment.getMaxScore());
		
		
		return submissionResponseDTO;
	}
}
