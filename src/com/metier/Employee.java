package com.metier;

public class Employee {
	
	public  int id;
	private String nom;
	private String prenom;
	private String position;
	private String login;
	private String pass;
	
	
	public Employee(int id, String nom, String prenom, String position, String login, String pass) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.position = position;
		this.login = login;
		this.pass = pass;
	}

	public  int getId() {
		return id;
	}

	public void setId(int idd) {
		this.id = idd;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
	
}
