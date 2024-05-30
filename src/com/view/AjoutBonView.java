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
import com.dao.RespAchatDAO;
import com.dao.notFoundExp;
import com.metier.BonAchat;

public class AjoutBonView{

	private JFrame wind;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;

	public AjoutBonView() {
		initialize();
	}
	
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("ajout bon d'achat");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 666, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter Bon D'achat");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(241, 30, 195, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(98, 89, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("fournisseur");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(98, 228, 99, 23);
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
		lblNewLabel_1_1.setBounds(98, 276, 77, 20);
		contentPane.add(lblNewLabel_1_1);
		
		// combo boxes
		
		List<String> l2 = RespAchatDAO.getIDFrns();
		String[] frns = l2.toArray(new String[l2.size()]);
		
		List<String> l3 = RespAchatDAO.getIDArts();
		String[] arts = l3.toArray(new String[l2.size()]);
		
		JComboBox comboBox_f = new JComboBox(frns);
		comboBox_f.setBounds(207, 230, 115, 22);
		contentPane.add(comboBox_f);
		
		JLabel lblNewLabel_5_1 = new JLabel("article");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(98, 179, 99, 23);
		contentPane.add(lblNewLabel_5_1);
		
		JComboBox comboBox_2_1 = new JComboBox(arts);
		comboBox_2_1.setBounds(207, 181, 115, 22);
		contentPane.add(comboBox_2_1);
		
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
		textField_2.setBounds(207, 276, 115, 20);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("date");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(98, 132, 99, 23);
		contentPane.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 132, 115, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
			
			btnNewButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					try {
						
						if(textField.getText().equals("") || 
								textField_1.getText().equals("") ||
								textField_2.getText().equals("") ||
								comboBox_f.getItemCount() == 0 ||
								comboBox_2_1.getItemCount() == 0 ||
								!EmpInterfaceDAO.checkNum(textField.getText()) ||
								!EmpInterfaceDAO.checkNum(textField_2.getText())
								) {
							throw new notFoundExp();
						}
				else {
					try {
						if(EmpInterfaceDAO.verifHoleDate(textField_1.getText())) {
	                        BonAchat b = new BonAchat(
									Integer.parseInt(textField.getText()),
									textField_1.getText(),
									"non valide", 
									Integer.parseInt(String.valueOf(comboBox_f.getSelectedItem())),
									Integer.parseInt(String.valueOf(comboBox_2_1.getSelectedItem())),
									Integer.parseInt(textField_2.getText())
									);
	                        
	                        if(RespAchatDAO.addBonDachat(b)) {
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
				}catch(notFoundExp ex) {
					JOptionPane.showMessageDialog(null, "champs non valides!", null, JOptionPane.ERROR_MESSAGE);
				}
						
				}
					
			});
		
	}
	
	public void show() {
		wind.setVisible(true);
	}
}
