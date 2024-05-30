package com.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.EmpInterfaceDAO;
import com.dao.RespVenteDAO;
import com.dao.notFoundExp;
import com.metier.Commande;

public class AjoutComView{

	private JFrame wind;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;

	public AjoutComView() {
		initialize();
	}
	
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("ajout commande");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 666, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter Commande");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(254, 30, 177, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(98, 89, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("client id");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(98, 179, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("article");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(98, 217, 77, 23);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setForeground(Color.BLACK);
		textField.setBounds(207, 89, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("quantite");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(98, 265, 77, 20);
		contentPane.add(lblNewLabel_1_1);
		
		// combo boxes
		
		List<String> l = RespVenteDAO.getIDClients();
		String[] clients = l.toArray(new String[l.size()]);
		
		JComboBox comboBox_1 = new JComboBox(clients);
		comboBox_1.setBounds(207, 177, 115, 22);
		contentPane.add(comboBox_1);
		
		List<String> l2 = RespVenteDAO.getDesgs();
		String[] arts = l2.toArray(new String[l2.size()]);
		JComboBox comboBox_2 = new JComboBox(arts);
		comboBox_2.setBounds(207, 219, 115, 22);
		contentPane.add(comboBox_2);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(360, 369, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("fermer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(469, 369, 89, 23);
		contentPane.add(btnNewButton_1);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.BLACK);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(207, 265, 115, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("date");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(98, 130, 99, 23);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(207, 130, 115, 23);
		contentPane.add(textField_1);
		
				
				btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().equals("") || 
							textField_1.getText().equals("") ||
							textField_2.getText().equals("") ||
							comboBox_1.getItemCount() == 0 ||
							comboBox_2.getItemCount() == 0 ||
							!EmpInterfaceDAO.checkNum(textField.getText()) ||
							!EmpInterfaceDAO.checkNum(textField_2.getText())
							) {
						throw new notFoundExp();
					}
					else {
						try {
							if(EmpInterfaceDAO.verifHoleDate(textField_1.getText()) ) {
								Commande c = new Commande(
								Integer.parseInt(textField.getText()),
								textField_1.getText(),
								"pret",
								Integer.parseInt(String.valueOf(comboBox_1.getSelectedItem())),
								Integer.parseInt(textField_2.getText()),
								RespVenteDAO.getIDArticle(String.valueOf(comboBox_2.getSelectedItem()))
								);
									
								if(RespVenteDAO.passerCom(c)) {
									JOptionPane.showMessageDialog(btnNewButton, "ajouté");
									wind.dispose();
								}
							}
							else {
								throw new notFoundExp();
								}
							
							}catch(notFoundExp dt) {
								JOptionPane.showMessageDialog(null, "date invalid!", null, JOptionPane.ERROR_MESSAGE);
							}
					}
				
				}catch(notFoundExp ne) {
					JOptionPane.showMessageDialog(null, "champs non valides!", null, JOptionPane.ERROR_MESSAGE);
				}
				
			}
				
		});
		
	
	}	
	
	public void show() {
		wind.setVisible(true);
	}
}
