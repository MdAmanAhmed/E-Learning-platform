package com.cognizant.project.elearning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.entity.User;
import com.cognizant.project.elearning_platform.service.UserService;

@RestController
public class UserController {
@Autowired
UserService userService;


@PostMapping("/addUser")
public User addUser(User user) {
	return userService.addUser(user);
}
@PostMapping("/addStudent")
public User addStudent(Student student) {
	return addUser(student);
}
@PostMapping("/addInstructor")
public User addInstructor(Instructor instructor) {
	return addUser(instructor);
}


@GetMapping("/viewAllUser")

public List<User> viewAllUser() {
	return userService.viewAllUser();
}

@GetMapping("viewAllStudent")
public List<User> viewAllStudent() {
	return userService.viewAllStudent();
}


	
}
