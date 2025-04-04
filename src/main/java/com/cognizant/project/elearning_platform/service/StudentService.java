package com.cognizant.project.elearning_platform.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.repository.StudentRepository;

@Service
public class StudentService {
@Autowired
	ModelMapper modelMapper;
@Autowired
StudentRepository studentRepository;
	public StudentDTO addStudent(StudentDTO studentDTO) {
		Student student=modelMapper.map(studentDTO,Student.class);
		studentRepository.save(student);
		return modelMapper.map(student, StudentDTO.class);
	}
}
