package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Jugador;
import com.example.demo.model.dto.RegisterRequest;

public interface JugadorService {
	
	String registerUser(RegisterRequest request);
	
	
	Optional<Jugador> readById(String id);   // R	
	
	Optional<Jugador> readByEmail(String email);
		
	void update(Jugador jugador);	// U	
	
	void delete(String id);	// D
	
	List<Jugador> getAll();
}
