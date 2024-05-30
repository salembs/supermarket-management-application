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
import com.metier.Article;

public class ModifArtView {

	private JFrame wind;
	private String[] result;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public ModifArtView() {
		initialize();
	}
	
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("modifier article");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 666, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Modifier article");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(255, 30, 191, 32);
		contentPane.add(lblNewLabel);
		
		
			List<String> l = RespAchatDAO.getIDArts();
			result = l.toArray(new String[l.size()]);

			
		JLabel lblNewLabel_1 = new JLabel("selectionner");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(98, 120, 108, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("designation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(98, 162, 99, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("prix");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(98, 200, 77, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("categorie");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(98, 241, 77, 27);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("stock");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(98, 279, 77, 34);
		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(207, 159, 115, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(207, 203, 115, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setForeground(Color.BLACK);
		textField_3.setBounds(207, 245, 115, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setForeground(Color.BLACK);
		textField_4.setBounds(207, 286, 115, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox(result);
		comboBox.setBounds(207, 118, 115, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(textField_1.getText().equals("") ||
							textField_2.getText().equals("") ||
							textField_3.getText().equals("") ||
							textField_4.getText().equals("") ||
							comboBox.getItemCount() == 0 ||
							!EmpInterfaceDAO.checkNum(textField_2.getText()) ||
							!EmpInterfaceDAO.checkNum(textField_4.getText())
							) {
						throw new notFoundExp();
					}
				else {
					int id = Integer.parseInt(String.valueOf(comboBox.getSelectedItem()));
					
					RespAchatDAO.suppArt(id);
						
					Article a = new Article(
							id,
							textField_1.getText(),
							Float.parseFloat(textField_2.getText()),
							textField_3.getText(),
							Integer.parseInt(textField_4.getText())
							);
							
						if (RespAchatDAO.ajoutArt(a)) {
							JOptionPane.showMessageDialog(btnNewButton, "modifié");
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
