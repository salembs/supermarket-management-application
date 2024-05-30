package com.controller;

import com.dao.RespAchatDAO;
import com.metier.Article;
import com.metier.BonAchat;
import com.metier.Employee;
import com.metier.Personne;
import com.view.RespAchatView;

public class respAchatController{
	
	private Employee model;
	private RespAchatView view;
	
	public respAchatController(Employee model, RespAchatView view) {
		this.model = model;
		this.view = view;
	}

	public void ajoutFrn(Personne f) {
		RespAchatDAO.addFrn(f);
	}
	
	public boolean suppFrn(int idFrn) {
		return RespAchatDAO.delFrn(idFrn);
	}
	
	public void addBonDachat(BonAchat b) {
		RespAchatDAO.addBonDachat(b);
	}
	
	public boolean suppBonDachat(int inBon) {
		return RespAchatDAO.suppBonDachat(inBon);
	}
	
	
	public void ajoutArt(Article a) {
		RespAchatDAO.ajoutArt(a);
	}
	
	public boolean suppArt(int idArt) {
		return RespAchatDAO.suppArt(idArt);
	}

	public void visualiser() {
		view.show();
	}

}
