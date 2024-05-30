package com.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.dao.RespVenteDAO;
import com.metier.ClientModel;
import com.metier.Personne;


public class PersonneView {
	
		
		private JFrame wind;
		private JTable jTable;
		private ClientModel cltModel;
	    
	    public PersonneView() {
	    	initialize();
	    }
	    
	    public void initialize() {
	    	
	    	wind = new JFrame("Clients");
	    	wind.setTitle("personnes");
	    	wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	wind.getContentPane().setLayout(new BorderLayout());
	    	wind.setSize(800, 600);
	        cltModel = new ClientModel();
	        jTable=new JTable(cltModel);
	        

	        JScrollPane jScrollPane=new JScrollPane(jTable);
	       	wind.getContentPane().add(jScrollPane,BorderLayout.CENTER);
			
			List<Personne> pers = RespVenteDAO.getAllClient();
			cltModel.loadData(pers);
	    }
	    
	
    public void show() {
    	wind.setVisible(true);
    }

}

