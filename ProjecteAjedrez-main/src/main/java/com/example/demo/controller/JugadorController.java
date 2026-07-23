package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PartidaException;
import com.example.demo.exception.PlayerException;
import com.example.demo.model.Jugador;
import com.example.demo.model.Role;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.service.JugadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jugadors")
@Tag(name = "Players", description = "API for managing chess players")
public class JugadorController {

	private final JugadorService jugadorService;

    @Autowired
	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}
    
	@Operation(summary = "Create a new player", description = "Registers a new chess player in the system")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Player successfully created"),
		@ApiResponse(responseCode = "406", description = "Player creation not allowed or invalid data")
	})
	@PostMapping("/create")
	public ResponseEntity<RegisterRequest> create(@Valid @RequestBody RegisterRequest jugador) {
		try {
			jugadorService.registerUser(jugador);
		} catch (IllegalStateException e) {
			throw new PlayerException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(jugador, HttpStatus.CREATED);
	}


	@Operation(summary = "Get all players", description = "Returns a list of all registered chess players")
	@ApiResponse(responseCode = "200", description = "Players retrieved successfully")
	@GetMapping("/cerca")
	public List<Jugador> getAll() {
	    return jugadorService.getAll();
	}

	@Operation(summary = "Get all players", description = "Returns a list of all registered chess players")
	@ApiResponse(responseCode = "200", description = "Players retrieved successfully")
	@GetMapping("/cerca/{id}")
	public  ResponseEntity<Jugador>  cercaPerId(@Parameter(description = "ID of the player", example = "player123") @PathVariable String id) {
		Optional<Jugador> optional=jugadorService.readById(id);
		
		if(!optional.isPresent()) {
			throw new PlayerException("Jugador no encontrado con id: " + id , HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
	}
	
	@Operation(summary = "Get player by email", description = "Returns the chess player matching the specified email")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Player found successfully"),
		@ApiResponse(responseCode = "404", description = "Player not found")
	})
	@GetMapping("/cerca/email")
	public  ResponseEntity<Jugador>  cercaPerEmail(@Parameter(description = "Email of the player", example = "jugador@dominio.com") @RequestParam String email) {

		Optional<Jugador> optional= jugadorService.readByEmail(email);
		
		if(!optional.isPresent()) {
			throw new PlayerException("Usuari no existent amb aquest email: " + email, HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<>(optional.get(), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Parameter(description = "ID of the player to delete", example = "player123") @PathVariable String id) {		
		try {
			jugadorService.delete(id);
		} catch (IllegalStateException e) {
			throw new PlayerException(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}