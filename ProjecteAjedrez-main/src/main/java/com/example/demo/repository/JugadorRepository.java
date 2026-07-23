package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Jugador;

public interface JugadorRepository extends MongoRepository <Jugador, String>{
	   Optional<Jugador> findByEmail(String email);
}
