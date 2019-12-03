package com.japarejo.springmvc.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("japarejo".equals(username)) {
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
	        list.add(new SimpleGrantedAuthority("ADMIN"));
			return new User("japarejo", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					list);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
