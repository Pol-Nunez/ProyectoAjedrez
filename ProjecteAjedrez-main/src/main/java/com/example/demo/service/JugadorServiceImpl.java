package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PlayerException;
import com.example.demo.model.Jugador;
import com.example.demo.model.JugadorLogin;
import com.example.demo.model.Role;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.repository.JugadorLoginRepository;
import com.example.demo.repository.JugadorRepository;

@Service
public class JugadorServiceImpl implements JugadorService {

	private final JugadorRepository jugadorRepo;

    @Autowired
    private JugadorLoginRepository loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired
	public JugadorServiceImpl(JugadorRepository jugadorRepo) {
		super();
		this.jugadorRepo = jugadorRepo;
	}
	

    @Override
    public String registerUser(RegisterRequest request) {

        // Validaciones básicas
        if (request.getUsername() == null || request.getPassword() == null) {
            throw new PlayerException("Username y password son obligatorios");
        }

        if (request.getEmail() == null ) {
            throw new PlayerException("El email es obligatorio");
        }

        if(request.getSexe() == null) {
            throw new PlayerException("El sexe es obligatorio");
        	
        }
        
        if(request.getNivell() == null) {
            throw new PlayerException("El nivell es obligatorio");
        }
        
        if(request.getEdat() == null) {
            throw new PlayerException("La edat es obligatorio");
        }
        
        // Verificar duplicados
        if (jugadorRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new PlayerException("Ya existe un jugador con ese email");
        }

        if (loginRepo.findByUsername(request.getUsername()).isPresent()) {
            throw new PlayerException("Ya existe un jugado con ese username");
        }
        

        // Crear Jugador (perfil)
        Jugador jugador = new Jugador();
        jugador.setNom(request.getNom());
        jugador.setEdat(String.valueOf(request.getEdat()));
        jugador.setSexe(request.getSexe());
        jugador.setNivell(request.getNivell());
        jugador.setEmail(request.getEmail());
        jugador.setRole(Role.USER);

        jugadorRepo.save(jugador);

        // Crear JugadorLogin (credenciales)
        JugadorLogin login = new JugadorLogin();
        login.setUsername(request.getUsername());
        login.setPassword(passwordEncoder.encode(request.getPassword()));
        login.setRole(Role.USER);

        loginRepo.save(login);

        return jugador.getId();
    }

	public Optional<Jugador> readById(String id) {
		return jugadorRepo.findById(id);
	}

	public Optional<Jugador> readByEmail(String email) {
		return jugadorRepo.findByEmail(email);
	}

	public void update(Jugador jugador) {
		if (jugador.getId() == null) {
			throw new IllegalStateException("No es pot actualizar un usuari amb id null");
		}
		if (!jugadorRepo.existsById(jugador.getId())) {
			throw new IllegalStateException("L'usuari amb " + jugador.getId() + " no existeix. No es pot actualitzar.");
		}
		jugadorRepo.save(jugador);
	}

	public void delete(String id) {
		if (id == null) {
			throw new IllegalStateException("No es pot eliminar un jugador amb id null");
		}
		if (jugadorRepo.findById(id).isPresent()) {
			jugadorRepo.deleteById(id);
		} else {
			throw new IllegalStateException("El jugador amb " + id + " no existeix. No es pot eliminar.");
		}
	}

	public List<Jugador> getAll() {
		return jugadorRepo.findAll();
	}
}