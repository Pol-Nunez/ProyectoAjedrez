package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Jugador;
import com.example.demo.model.Mesa;
import com.example.demo.model.Partida;
import com.example.demo.model.Reserva;
import com.example.demo.repository.JugadorRepository;
import com.example.demo.repository.MesaRepository;
import com.example.demo.repository.PartidaRepository;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	private final ReservaRepository repository;
	private final JugadorRepository jugadorRepository;
	private final PartidaRepository partidaRepository;
	private final MesaRepository mesaRepository;
	
	public ReservaServiceImpl(ReservaRepository repository, JugadorRepository jugadorRepository, PartidaRepository partidaRepository, MesaRepository mesaRepository) {
		super();
		this.repository = repository;
		this.jugadorRepository = jugadorRepository;
		this.partidaRepository = partidaRepository;
		this.mesaRepository = mesaRepository;
	}

	@Override
	public Reserva create(Reserva reserva) {

	    // Obtenir taula
	    Mesa mesa = mesaRepository.findById(reserva.getMesa().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada"));
	    reserva.setMesa(mesa);

	    // Obtenir jugador
	    Jugador jugador = jugadorRepository.findById(reserva.getJugador().getId())
	            .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado"));
	    reserva.setJugador(jugador);

	    // Buscar si ya existe otra reserva para esa mesa/fecha/hora sin partida
	    Optional<Reserva> otraReserva = repository
	            .findFirstByMesaIdAndFechaAndHoraAndPartidaIsNull(
	                    mesa.getId(),
	                    reserva.getFecha(),
	                    reserva.getHora()
	            );

	    if (otraReserva.isPresent()) {

	        Reserva r1 = otraReserva.get();

	        if (!r1.getJugador().getId().equals(jugador.getId())) {

	            Partida partida = new Partida(
	                    null,
	                    r1.getJugador().getId(),  
	                    jugador.getId(),          
	                    mesa,
	                    false                     
	            );

	            partidaRepository.save(partida);

	            r1.setPartida(partida);
	            reserva.setPartida(partida);

	            repository.save(r1);
	        }
	    }

	    return repository.save(reserva);
	}

	@Override
	public Optional<Reserva> findById(String id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Reserva> findByPlayer(String id) {
		return Optional.empty();
	}

	@Override
	public Optional<Reserva> findByGame(String id) {
		return Optional.empty();
	}

	@Override
	public boolean delete(String id) {
		boolean borrado = false;
		if (repository.existsById(id)) {
			repository.deleteById(id);
			borrado = true;
		}
		return borrado;
	}

	@Override
	public List<Reserva> listAll() {
		return repository.findAll();
	}
	
}