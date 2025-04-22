package com.cognizant.project.elearning.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning.entity.User;
import com.cognizant.project.elearning.exception.AllException.UserNotExist;
import com.cognizant.project.elearning.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	User user = userRepository.findByEmail(username).orElseThrow(() -> 
								new UserNotExist("User with Email "+username+" didnt exist"));
	return new UserPrincipal(user);
	}

}
