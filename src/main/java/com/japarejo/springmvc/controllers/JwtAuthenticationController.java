package com.japarejo.springmvc.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.DisabledException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.japarejo.springmvc.model.services.JwtUserDetailsService;
import com.japarejo.springmvc.util.JwtRequest;
import com.japarejo.springmvc.util.JwtResponse;
import com.japarejo.springmvc.util.JwtTokenUtil;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/*@Autowired
	private JwtUserDetailsService userDetailsService;*/

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		Authentication authentication=authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());		

		final String token = jwtTokenUtil.generateToken(authentication.getName());
		return ResponseEntity.ok(new JwtResponse(token));

	}

	private Authentication authenticate(String username, String password) throws Exception {
		Authentication result=null;
		try {
			result=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		return result;
	}

}
