package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.demo.model.Role;

import io.swagger.v3.oas.annotations.media.Schema;

@Document(collection = "jugadors")
@Schema(description = "Entity representing a chess player in the system")
public class Jugador {
	@Id
	private String id;
	@Field("nom_jugador")
	private String nom;
	private String edat;
	private String sexe;
	private String nivell;
	private String password;
	private String email;
	private Role role;
	
	
	public Jugador() {}
	
	public Jugador(String id, String nom, String edat, String sexe, String nivell, String password, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.edat = edat;
		this.sexe = sexe;
		this.nivell = nivell;
		this.password = password;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEdat() {
		return edat;
	}
	public void setEdat(String edat) {
		this.edat = edat;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getNivell() {
		return nivell;
	}
	public void setNivell(String nivell) {
		this.nivell = nivell;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
