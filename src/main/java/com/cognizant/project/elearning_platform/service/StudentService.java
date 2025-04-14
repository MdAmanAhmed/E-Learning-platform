package com.cognizant.project.elearning_platform.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.dto.StudentResponseDTO;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.StudentRepository;

@Service
public class StudentService {
@Autowired
	ModelMapper modelMapper;
@Autowired
StudentRepository studentRepository;
	public StudentResponseDTO viewStudent(int studentId) {
		Student student=studentRepository.findById(studentId).orElseThrow(()->new StudentDetailNotFound("Student with Id "+studentId+" not found."));
		return modelMapper.map(student, StudentResponseDTO.class);
	}
	
	
	
}
