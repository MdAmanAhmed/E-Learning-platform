package com.cognizant.project.elearning_platform.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.project.elearning_platform.entity.User;

public class UserPrincipal implements UserDetails{
	User user;
public UserPrincipal(User user) {
	this.user=user;
}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return String.valueOf(user.getPassword());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return String.valueOf(user.getUserId());
	}

}
