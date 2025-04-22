package com.cognizant.project.elearning.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.dto.LoginRequestDTO;
import com.cognizant.project.elearning.dto.LoginResponseDTO;
import com.cognizant.project.elearning.dto.RegisterRequestDTO;
import com.cognizant.project.elearning.dto.RegisterResponseDTO;
import com.cognizant.project.elearning.entity.Instructor;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.entity.User;
import com.cognizant.project.elearning.exception.AllException.UserNotExist;
import com.cognizant.project.elearning.repository.InstructorRepository;
import com.cognizant.project.elearning.repository.StudentRepository;
import com.cognizant.project.elearning.repository.UserRepository;
import com.cognizant.project.elearning.security.JWTService;

@Service
public class AuthenticationService {
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InstructorRepository instructorRepository;
	
	@Autowired
	private JWTService jwtService;

	@Autowired
	AuthenticationManager authenticationManager ;

	BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
		User user=modelMapper.map(registerRequestDTO,User.class);
		user.setPassword(encoder.encode(user.getPassword()));
		User saveduser=null;
		if(user.getRole().name().equals("ROLE_STUDENT")) {
			saveduser=studentRepository.save(modelMapper.map(user, Student.class));
		}
		else if(user.getRole().name().equals("ROLE_INSTRUCTOR")) {
			saveduser=instructorRepository.save(modelMapper.map(user, Instructor.class));
		}
		else
		{
		throw	new  UserNotExist("User with Role "+user.getRole().name()+" didnt exist");
		}
		
		return modelMapper.map(saveduser, RegisterResponseDTO.class);
	
	}
	
	public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
	
		User user=userRepository.findByEmail(loginRequestDTO.getEmail())
				.orElseThrow(()->new  UserNotExist("User with Email "+loginRequestDTO.getEmail()+" didnt exist"));
		LoginResponseDTO loginResponseDTO=modelMapper.map(user, LoginResponseDTO.class);
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));
	
		loginResponseDTO.setToken( jwtService.generateToken(user));
		return loginResponseDTO;
	
		
	}

}
