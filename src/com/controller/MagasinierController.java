package com.controller;

import java.util.List;

import com.dao.MagasinierDAO;
import com.metier.Article;
import com.metier.Employee;
import com.view.MagasinierView;

public class MagasinierController {
	
	private Employee model;
	private MagasinierView view;
	
	public MagasinierController(Employee model, MagasinierView view) {
		this.model = model;
		this.view = view;
	}
	
	public void valideEntrerStk(int id) {
		MagasinierDAO.valideEntrerStk(id);
	}
	
	public List<Article> getAllProducts() {
		return MagasinierDAO.getAllProducts();
	}
	
	public void visualiser() {
		view.show();
	}

	
	

}
