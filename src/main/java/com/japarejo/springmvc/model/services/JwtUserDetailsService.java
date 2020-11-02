package com.japarejo.springmvc.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.japarejo.springmvc.model.entities.Usuario;
import com.japarejo.springmvc.model.repositories.UsuarioRepository;

@Service
public class JwtUserDetailsService {
	@Autowired
	UsuarioRepository userRepo;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario=userRepo.findByLogin(username);
		if (usuario!=null) {
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
	        list.add(new SimpleGrantedAuthority(usuario.getPermiso()));
			return new User(usuario.getLogin(),usuario.getPassword(),list);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
