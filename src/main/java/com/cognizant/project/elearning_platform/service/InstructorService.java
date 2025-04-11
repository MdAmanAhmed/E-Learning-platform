package com.cognizant.project.elearning_platform.service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.InstructorDTO;
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

	public InstructorDTO addInstructor(InstructorDTO instructorDTO){
		
		Instructor instructor= modelMapper.map(instructorDTO,Instructor.class);
		instructor.setPassword(encoder.encode(instructor.getPassword()));
		instructorRepository.save(instructor);
		
		return modelMapper.map(instructor, InstructorDTO.class);
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
	
	
	public InstructorDTO viewInstructor(int instructorId) {
		Optional<Instructor> container=instructorRepository.findById(instructorId);
		if(!container.isPresent()) {
			throw new InstructorDetailNotFound();
		}
		Instructor instructor=container.get();
		
		return modelMapper.map(instructor,InstructorDTO.class);
	}
	
}
