package com.controller;


import com.dao.RespVenteDAO;
import com.metier.Commande;
import com.metier.Employee;
import com.metier.Personne;
import com.view.RespVenteView;

public class respVenteController{
	private Employee model;
	private RespVenteView view;
	
	

	public respVenteController(Employee model, RespVenteView view) {
		this.model = model;
		this.view = view;
	}
	
	public void ajoutClient(Personne c) {
		// مفروض من ال interface graphique	
		RespVenteDAO.ajoutClient(c);
	}
	
	public boolean suppClient(int id) {
		
		return RespVenteDAO.suppClient(id);
		
	}
	
	//public void modifClient(Client c) {}
	
	public void passerCom(Commande co) {
		RespVenteDAO.passerCom(co);
		
	}
	
	public boolean annulerCom(int idco) {
		return RespVenteDAO.annulerCom(idco);
	}

	public void visualiser() {
		view.show();
	}
	
	// public void reclamation(Commande co) {}

	
	
	
	
	
	
}
