package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.dao.MagasinierDAO;
import com.metier.Article;
import com.metier.ArticleModel;


public class ArticleView {
	
		
		private JFrame wind;
		private JTable jTable;
		private ArticleModel artModel;
	    
	    public ArticleView() {
	    	initialize();
	    }
	    
	    public void initialize() {
	    	
	    	wind = new JFrame("Articles");
	    	wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	wind.getContentPane().setLayout(new BorderLayout());
	    	wind.setSize(800, 600);
	        JPanel jPanelN=new JPanel();
	        jPanelN.setBackground(Color.DARK_GRAY);

	        jPanelN.setLayout(new FlowLayout());
	        wind.getContentPane().add(jPanelN,BorderLayout.SOUTH);
	        artModel = new ArticleModel();
	        jTable=new JTable(artModel);
	        

	        JScrollPane jScrollPane=new JScrollPane(jTable);
	       	wind.getContentPane().add(jScrollPane,BorderLayout.CENTER);
	       
	        JButton btnNewButton = new JButton("Fermer");
	        btnNewButton.setBackground(Color.WHITE);
	        btnNewButton.setForeground(Color.BLACK);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jPanelN.add(btnNewButton);
	        
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wind.dispose();
				}
			});
			
				List<Article> arts = MagasinierDAO.getAllProducts();
				artModel.loadData(arts);

	    }
	    
	
    public void show() {
    	wind.setVisible(true);
    }

}
