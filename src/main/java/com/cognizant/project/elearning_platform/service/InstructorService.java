package com.cognizant.project.elearning_platform.service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.InstructorResponseDTO;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning_platform.repository.InstructorRepository;

@Service
public class InstructorService {
@Autowired
	ModelMapper modelMapper;
@Autowired
InstructorRepository instructorRepository ;

BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

	public InstructorResponseDTO addInstructor(InstructorResponseDTO instructorDTO){
		
		Instructor instructor= modelMapper.map(instructorDTO,Instructor.class);
		instructor.setPassword(encoder.encode(instructor.getPassword()));
		instructor=instructorRepository.save(instructor);
		
		return modelMapper.map(instructor, InstructorResponseDTO.class);
	}
	
	
	public String removeInstructor(int instructorId) {
	Optional<Instructor> container=instructorRepository.findById(instructorId);
	if(!container.isPresent()) {
		return "failed no one";
	}
	Instructor instructor=container.get();
	instructorRepository.delete(instructor);
	return "successful";
	
	}
	
	
	public InstructorResponseDTO viewInstructor(int instructorId) {
Instructor instructor=instructorRepository.findById(instructorId).orElseThrow(
	()->new InstructorDetailNotFound("Instructor with Id "+instructorId+" not found."));
		return modelMapper.map(instructor,InstructorResponseDTO.class);
	}
	
}
