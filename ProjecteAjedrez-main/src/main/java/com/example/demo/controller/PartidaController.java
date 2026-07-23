package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.PartidaException;
import com.example.demo.model.Partida;
import com.example.demo.service.PartidaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/partides")
@Tag(name = "Matches", description = "API for managing chess matches")
public class PartidaController {

	private final PartidaService partidaService;

	@Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

	@Operation(summary = "Create a new match", description = "Creates a new chess match between two players")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Match successfully created"),
			@ApiResponse(responseCode = "400", description = "Invalid match data"),
			@ApiResponse(responseCode = "500", description = "Unable to create the match") })
	@PostMapping
	public ResponseEntity<Partida> create(@RequestBody Partida partida) {
		try {
			partidaService.createPartida(partida);
		} catch (Exception e) {
			throw new PartidaException("Unable to create match");
		}
		return new ResponseEntity<>(partida, HttpStatus.CREATED);
	}

	@Operation(summary = "Get all matches", description = "Returns a list of all registered chess matches")
	@ApiResponse(responseCode = "200", description = "List of matches retrieved successfully")
	@GetMapping("/cercatots")
	public List<Partida> getAll() {
		return partidaService.getPartides();
	}

	@Operation(summary = "Get matches by player ID", description = "Returns all matches where the specified player has participated")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Matches retrieved successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid player ID supplied") })
	@GetMapping("/cercaperid")
	public List<Partida> getPartidesJugador(
			@Parameter(description = "ID of the player", example = "player123") @RequestParam String jugadorId) {
		return partidaService.getPartidesPerJugador(jugadorId);
	}

	@Operation(summary = "Get matches won by a player", description = "Returns all matches won by the specified player")
	@ApiResponse(responseCode = "200", description = "Winning matches retrieved successfully")
	@GetMapping("/cercaguanyades")
	public List<Partida> getPartidesGuanyadesJugador(
			@Parameter(description = "ID of the player", example = "player123") @RequestParam String jugadorId) {
		return partidaService.getPartidesGuanyadesJugador(jugadorId);
	}

	@Operation(summary = "Get matches lost by a player", description = "Returns all matches lost by the specified player")
	@ApiResponse(responseCode = "200", description = "Losing matches retrieved successfully")
	@GetMapping("/cercaperdudes")
	public List<Partida> getPartidesPerdudesJugador(
			@Parameter(description = "ID of the player", example = "player123") @RequestParam String jugadorId) {
		return partidaService.getPartidesPerdudesJugador(jugadorId);
	}

	@Operation(summary = "Finish a match", description = "Marks a match as finished and sets the winner")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Match finished successfully"),
			@ApiResponse(responseCode = "404", description = "Match not found") })
	@PostMapping("/finalitzar")
	public ResponseEntity<Partida> finalitzarPartida(
			@Parameter(description = "Match ID", example = "65a8f1c23b9a") @RequestParam String partidaId) {

		Partida partida = partidaService.finalitzarPartida(partidaId);
		return new ResponseEntity<>(partida, HttpStatus.OK);
	}
}