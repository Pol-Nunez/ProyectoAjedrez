package com.example.demo.model.dto;



public class RegisterRequest {
     
	private String username;
	
	private String password;
	
     
	private String nom;
	
     
	private Integer edat;


    
	private String sexe;

     
	private String nivell;

     
	private String email;

	public RegisterRequest(String username, String password, String nom, Integer edat, String sexe, String nivell,
			String email) {
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.edat = edat;
		this.sexe = sexe;
		this.nivell = nivell;
		this.email = email;
	}


	// Getters y setters
	

	public RegisterRequest() {
	}	
	
	public String getNom() {
		return nom;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEdat() {
		return edat;
	}

	public void setEdat(Integer edat) {
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
}