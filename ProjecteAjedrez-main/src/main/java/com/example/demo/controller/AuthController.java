package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Jugador;
import com.example.demo.model.JugadorLogin;
import com.example.demo.model.Role;
import com.example.demo.model.dto.LoginRequest;
import com.example.demo.model.dto.RegisterRequest;
import com.example.demo.repository.JugadorLoginRepository;
import com.example.demo.repository.JugadorRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.JugadorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private JugadorRepository playerRepo;
    
    @Autowired
    private JugadorLoginRepository loginRepo;
    
    @Autowired
    private JugadorService jugadorService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Obtener usuario de la base de datos para saber su rol
        Optional<JugadorLogin> optionalPlayer= loginRepo.findByUsername(request.getUsername());
              
        if(!optionalPlayer.isPresent()) {
        	return ResponseEntity.status(401).body("Jugador no trobat");
        }
        
        JugadorLogin playerLog = optionalPlayer.get();
        
        String token = jwtUtil.generateToken(request.getUsername(),playerLog.getRole());
        return ResponseEntity.ok(token);
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
    	String id = jugadorService.registerUser(request);
    	
        return ResponseEntity.ok("Jugador registrat correctament amb id: " + id);
    }
}