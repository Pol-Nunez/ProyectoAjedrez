package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.demo.model.Reserva;
import com.example.demo.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/reservas")
@Tag(name = "Reservations", description = "API for managing chess table reservations")
public class ReservaController {

    private final ReservaService service;

    @Autowired
    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @Operation(
        summary = "Create a new reservation",
        description = "Creates a new reservation for a chess table"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid reservation data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public Reserva create(@RequestBody Reserva reserva) {
        return service.create(reserva);
    }

    @Operation(
        summary = "Find reservation by ID",
        description = "Retrieves a reservation using its unique identifier (NIF)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reservation found"),
        @ApiResponse(responseCode = "404", description = "Reservation not found")
    })
    @GetMapping("/{id}")
    public EntityResponse<Reserva> findByID(
            @Parameter(description = "Unique identifier (NIF) of the reservation", example = "12345678A")
            @PathVariable("id") String id) {
        return null;
    }
}