package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Mesa;
import com.example.demo.service.MesaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mesas")
@Tag(name = "Mesas", description = "API para gestionar mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @Operation(summary = "Crear una nueva mesa", description = "Crea una nueva mesa con número, capacidad y ubicación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mesa creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la mesa inválidos")
    })
    

    @PostMapping("/create")
    public ResponseEntity<Mesa> create(@RequestBody Mesa mesa) {
        Mesa nuevaMesa = mesaService.save(mesa);
        return new ResponseEntity<>(nuevaMesa, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas las mesas", description = "Devuelve una lista de todas las mesas registradas")
    @ApiResponse(responseCode = "200", description = "Mesas recuperadas correctamente")

    @GetMapping("/cerca")
    public ResponseEntity<List<Mesa>> listAll() {
        List<Mesa> mesas = mesaService.findAll();
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @Operation(summary = "Buscar mesa por ID", description = "Devuelve la mesa que coincide con el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mesa encontrada"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    
    @GetMapping("cerca/{id}")
    public ResponseEntity<Mesa> findById(@Parameter(description = "ID de la mesa a buscar", example = "mesa123")@PathVariable String id) {
        return mesaService.findById(id)
                .map(mesa -> new ResponseEntity<>(mesa, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Eliminar mesa por ID", description = "Elimina la mesa que coincide con el ID proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mesa eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "ID de la mesa a eliminar", example = "mesa123") @PathVariable String id) {
        boolean eliminado = mesaService.deleteById(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}