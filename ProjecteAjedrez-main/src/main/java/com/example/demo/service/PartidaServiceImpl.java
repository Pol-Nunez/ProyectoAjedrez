package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Partida;
import com.example.demo.repository.PartidaRepository;

@Service
public class PartidaServiceImpl implements PartidaService{
	
	@Autowired
	PartidaRepository partidaRepository;
	
	@Override
	public String createPartida(Partida partida) {
		partidaRepository.save(partida);
		return partida.getId();
	}

	@Override
	public void finalizarPartida(Partida partida) {
		partida.setFinalitzada(true);
		
	}

	@Override
	public List<Partida> getPartides() {
		return partidaRepository.findAll();
	}
	
	@Override
	public List<Partida> getPartidesPerJugador(String jugadorId) {
		return partidaRepository.findAllByPlayer(jugadorId);
	}

	@Override
	public List<Partida> getPartidesGuanyadesJugador(String jugadorId) {
		return partidaRepository.findAllByWinnerId(jugadorId);
	}

	@Override
	public List<Partida> getPartidesPerdudesJugador(String jugadorId) {
		return partidaRepository.findAllByPlayerAndWinnerIdNot(jugadorId, jugadorId);
	}
	
	@Override
	public Partida finalitzarPartida(String partidaId) {

	    Partida partida = partidaRepository.findById(partidaId)
	            .orElseThrow(() -> new RuntimeException("Partida no encontrada"));

	    int random = (int) (Math.random() * 2) + 1;

	    // Asignar guanyador según el resultat aleatori
	    if (random == 1) {
	        partida.setWinnerId(partida.getPlayer1Id());
	    } else {
	        partida.setWinnerId(partida.getPlayer2Id());
	    }

	    partida.setFinalitzada(true);

	    return partidaRepository.save(partida);
	}

}
