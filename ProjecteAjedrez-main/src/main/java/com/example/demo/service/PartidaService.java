package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Partida;

public interface PartidaService {
	
	String createPartida(Partida partida); //devuelve el id de la mesa

	void finalizarPartida(Partida partida);
	List<Partida> getPartides();
	List<Partida> getPartidesPerJugador(String jugadorId);
	List<Partida> getPartidesGuanyadesJugador(String jugadorId);
	List<Partida> getPartidesPerdudesJugador(String jugadorId);
	Partida finalitzarPartida(String partidaId);
}
