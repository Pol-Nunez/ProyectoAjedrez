package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Reserva;

public interface ReservaService {
	public Reserva create(Reserva reserva);
	public Optional<Reserva> findById(String id); // ID de la reserva
	public Optional<Reserva> findByPlayer(String id); // ID del jugador
	public Optional<Reserva> findByGame(String id); // ID de la partida
	public boolean delete(String id);
	public List<Reserva> listAll(); // ArrayList de reservas
}
