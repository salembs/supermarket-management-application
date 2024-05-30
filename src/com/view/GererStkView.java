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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.dao.MagasinierDAO;
import com.metier.BonAchat;
import com.metier.BonachatModel;


public class GererStkView {
	
		
		private JFrame wind;
		private JTable jTable;
		private BonachatModel bonModel;
	    
	    public GererStkView() {
	    	initialize();
	    }
	    
	    public void initialize() {
	    	
	    	wind = new JFrame("Bons d'achat");
	    	wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	wind.getContentPane().setLayout(new BorderLayout());
	    	wind.setSize(800, 600);
	    	
	        JPanel jPanelN = new JPanel();
	        jPanelN.setBackground(Color.DARK_GRAY);

	        jPanelN.setLayout(new FlowLayout());
	        wind.getContentPane().add(jPanelN,BorderLayout.SOUTH);
	        bonModel = new BonachatModel();
	        jTable=new JTable(bonModel);
	        

	        JScrollPane jScrollPane=new JScrollPane(jTable);
	       	wind.getContentPane().add(jScrollPane,BorderLayout.CENTER);
	       
	       	JButton btnNewButton2 = new JButton("Valider");
	       	btnNewButton2.setBackground(Color.WHITE);
	       	btnNewButton2.setForeground(Color.BLACK);
	       	btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 16));
			jPanelN.add(btnNewButton2);
			
			List<BonAchat> bons = MagasinierDAO.getNonValidStk();
			bonModel.loadData(bons);
	        
			btnNewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jTable.getSelectedRow() != -1) {
					MagasinierDAO.valideEntrerStk(Integer.parseInt( bonModel.getValueAt(jTable.getSelectedRow(), 0) ) );
					JOptionPane.showMessageDialog(null, "validé", null, JOptionPane.INFORMATION_MESSAGE);
					bonModel.loadData(MagasinierDAO.getNonValidStk());}
					else {
						JOptionPane.showMessageDialog(null, "aucun choix selectionné!");
					}
				}
			});
	       	
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
	    }
	    
	
    public void show() {
    	wind.setVisible(true);
    }

}

