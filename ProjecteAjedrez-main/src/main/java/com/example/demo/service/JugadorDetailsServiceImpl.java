package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.JugadorLogin;
import com.example.demo.repository.JugadorLoginRepository;

@Service
public class JugadorDetailsServiceImpl implements UserDetailsService{
	
	   @Autowired
	   private JugadorLoginRepository repo;
	   @Override
	   public UserDetails loadUserByUsername(String username)
	           throws UsernameNotFoundException {
	       JugadorLogin user = repo.findByUsername(username)
	               .orElseThrow(() ->
	                       new UsernameNotFoundException("Jugador no trobat"));
	       return new org.springframework.security.core.userdetails.User(
	               user.getUsername(),
	               user.getPassword(),
	               List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
	       );
	   }
}
