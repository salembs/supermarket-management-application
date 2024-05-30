package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.dao.EmpInterfaceDAO;
import com.metier.Article;
import com.metier.ArticleModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class RechercheView {
	
		
		private JFrame wind;
		private JTable jTable;
		private ArticleModel artModel;
		private JTextField textField;
	    
	    public RechercheView(String who) {
	    	initialize(who);
	    }
	    
	    public void initialize(String who) {
	    	
	    	wind = new JFrame("Articles");
	    	wind.setTitle("articles");
	    	wind.getContentPane().setBackground(Color.DARK_GRAY);
	    	wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	wind.setSize(800, 600);
	        wind.getContentPane().setLayout(null);
	        JPanel jPanelN=new JPanel();
	        jPanelN.setBackground(Color.DARK_GRAY);
	        jPanelN.setBounds(0, 522, 784, 39);

	        jPanelN.setLayout(new FlowLayout());
	        wind.getContentPane().add(jPanelN);
	        artModel = new ArticleModel();
	        jTable=new JTable(artModel);
	        

	        JScrollPane jScrollPane=new JScrollPane(jTable);
	        jScrollPane.setBounds(0, 50, 784, 472);
	       	wind.getContentPane().add(jScrollPane, BorderLayout.CENTER);
	       
	        JButton btnNewButton = new JButton("Fermer");
	        btnNewButton.setBackground(Color.WHITE);
	        btnNewButton.setForeground(Color.BLACK);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jPanelN.add(btnNewButton);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.DARK_GRAY);
			panel.setBounds(0, 0, 784, 52);
			wind.getContentPane().add(panel);
			panel.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(311, 11, 177, 26);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("entrer le mot :");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(179, 11, 128, 26);
			panel.add(lblNewLabel);
			
			JButton btnNewButton_1 = new JButton("rechercher");
			btnNewButton_1.setBackground(Color.WHITE);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					List<Article> artcat = new ArrayList<>();
					if(who.equals("parmot")) {
						if(!textField.getText().equals("") && !(EmpInterfaceDAO.rechercheParArticle(textField.getText()).isEmpty()) ) {
							
								artModel.loadData(EmpInterfaceDAO.rechercheParArticle(textField.getText()));
							}
						else {
							artModel.loadData(artcat);
							textField.setText("");
							JOptionPane.showMessageDialog(null, "n'existe pas!");
						}
					}
					else {
						
						
						if(!EmpInterfaceDAO.rechercheParCategorie(textField.getText()).isEmpty() && !textField.getText().equals("")) {
							artModel.loadData(EmpInterfaceDAO.rechercheParCategorie(textField.getText()));}
							else {
								artModel.loadData(artcat);
								textField.setText("");
								JOptionPane.showMessageDialog(null, "n'existe pas!");
							}
					}
				}
			});
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNewButton_1.setBounds(507, 13, 114, 23);
			panel.add(btnNewButton_1);
	        
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					wind.dispose();
				}
			});
			

	    }
	    
	
    public void show() {
    	wind.setVisible(true);
    }
}

