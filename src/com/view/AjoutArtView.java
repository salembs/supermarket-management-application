package com.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.EmpInterfaceDAO;
import com.dao.RespAchatDAO;
import com.dao.notFoundExp;
import com.metier.Article;

public class AjoutArtView{

	private JFrame wind;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public AjoutArtView() {
		initialize();
	}
	
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("ajout article");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 666, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter Article");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(252, 30, 204, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(98, 118, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("designation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(98, 162, 99, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("prix");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(98, 206, 77, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("categorie");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(98, 254, 77, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("stock");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(98, 292, 77, 23);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setForeground(Color.BLACK);
		textField.setBounds(207, 118, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(207, 162, 115, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(207, 209, 115, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setForeground(Color.BLACK);
		textField_3.setBounds(207, 251, 115, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setForeground(Color.BLACK);
		textField_4.setBounds(207, 292, 115, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				
					if(textField.getText().equals("") || 
							textField_1.getText().equals("") ||
							textField_2.getText().equals("") ||
							textField_3.getText().equals("") ||
							textField_4.getText().equals("") ||
							!EmpInterfaceDAO.checkNum(textField.getText()) ||
							!EmpInterfaceDAO.checkNum(textField_2.getText()) ||
							!EmpInterfaceDAO.checkNum(textField_4.getText())
							
							) {
						throw new notFoundExp();
					}
					else {
					
						Article a = new Article(
								Integer.parseInt(textField.getText()),
								textField_1.getText(),
								Integer.parseInt(textField_2.getText()),
								textField_3.getText(),
								Integer.parseInt(textField_4.getText())
								);
								
							if (RespAchatDAO.ajoutArt(a)) {
								JOptionPane.showMessageDialog(btnNewButton, "ajouté");
								wind.dispose();
								
							}
					}
				}catch(notFoundExp ne) {
					JOptionPane.showMessageDialog(null, "champs non valides!", null, JOptionPane.ERROR_MESSAGE);
					
				}

			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(357, 350, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("fermer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(466, 350, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
	
	public void show() {
		wind.setVisible(true);
	}
	
}

