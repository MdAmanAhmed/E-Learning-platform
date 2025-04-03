package com.cognizant.project.elearning_platform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.entity.User;
import com.cognizant.project.elearning_platform.repository.UserRepository;


@Service
public class UserService {
	@Autowired
private	UserRepository userRepository;
	
	
	public User addUser(User user) {
		if(user instanceof Student) {
			userRepository.save((Student)user);
		}
		else if(user instanceof Instructor) {
			userRepository.save((Instructor)user);
		}
		return user;
	}
	
	public List<User> viewAllUser(){
		return userRepository.findAll();
	}
	
	public List<User> viewAllStudent(){
		return userRepository.findStudent();
	}
}
