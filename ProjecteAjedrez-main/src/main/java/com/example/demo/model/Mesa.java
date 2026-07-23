package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("mesas")
public class Mesa {
	
	@Id
	private String id;
	private String nom;
	private boolean disponibilidad;
	
	public Mesa(boolean disponibilidad) {
		super();
		this.disponibilidad = disponibilidad;
	}
		
	public Mesa() {}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}	
}