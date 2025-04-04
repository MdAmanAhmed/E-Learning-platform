package com.cognizant.project.elearning_platform.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.dto.InstructorDTO;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.repository.InstructorRepository;

@Service
public class InstructorService {
@Autowired
	ModelMapper modelMapper;
@Autowired
InstructorRepository instructorRepository ;
	public InstructorDTO addInstructor(InstructorDTO instructorDTO){
		
		Instructor instructor= modelMapper.map(instructorDTO,Instructor.class);
		
		instructorRepository.save(instructor);
		
		return modelMapper.map(instructor, InstructorDTO.class);
	}
}
