package com.cognizant.project.elearning_platform.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.project.elearning_platform.entity.User;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

User user = userRepository.findById(Integer.parseInt(username)).orElseThrow(() -> new StudentDetailNotFound());

return new UserPrincipal(user);
	}

}
