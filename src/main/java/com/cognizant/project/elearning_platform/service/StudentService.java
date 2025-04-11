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

import com.cognizant.project.elearning_platform.dto.CourseDTO;
import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.StudentRepository;

@Service
public class StudentService {
@Autowired
	ModelMapper modelMapper;
@Autowired
StudentRepository studentRepository;

@Autowired
private JWTService jwtService;

@Autowired
AuthenticationManager authenticationManager ;

BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


	public StudentDTO addStudent(StudentDTO studentDTO) {
		Student student=modelMapper.map(studentDTO,Student.class);
		student.setPassword(encoder.encode(student.getPassword()));
		studentRepository.save(student);
		return modelMapper.map(student, StudentDTO.class);
	}
	public StudentDTO viewStudent(int studentId) {
		Optional<Student> container=studentRepository.findById(studentId);
		if(!container.isPresent()) {
			throw new StudentDetailNotFound();
		}
		Student student=container.get();
		return modelMapper.map(student, StudentDTO.class);
	}
	
	
	public String verify(Student student) {
		// TODO Auto-generated method stub
	Authentication authentication=authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(String.valueOf(student.getUserId()),student.getPassword()));
	if(authentication.isAuthenticated()) {
		return jwtService.generateToken(String.valueOf(student.getUserId()));
	}
	return "failed";
	}
	
	
}
