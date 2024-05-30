package com.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dao.EmpInterfaceDAO;
import com.dao.notFoundExp;
import com.metier.Employee;

public class CreerView{

	private JFrame wind;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;

	public CreerView() {
		initialize();
	}
	
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("ajout article");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 690, 513);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ajouter employee");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(252, 30, 204, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(99, 89, 77, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("nom");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(99, 181, 99, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("prenom");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(99, 225, 77, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("user");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(99, 275, 77, 23);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setForeground(Color.BLACK);
		textField.setBounds(208, 89, 115, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setForeground(Color.BLACK);
		textField_1.setBounds(208, 181, 115, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setForeground(Color.BLACK);
		textField_2.setBounds(208, 228, 115, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setForeground(Color.BLACK);
		textField_4.setBounds(208, 275, 115, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		
		JLabel lblNewLabel_1_1 = new JLabel("position");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(99, 138, 77, 20);
		contentPane.add(lblNewLabel_1_1);
		
		String[] pos = {"resp vente", "resp achat", "magasinier"};
		JComboBox comboBox = new JComboBox(pos);
		comboBox.setBounds(208, 136, 115, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
				
					if(textField.getText().equals("") || 
							textField_1.getText().equals("") ||
							textField_2.getText().equals("") ||
							textField_4.getText().equals("") ||
							textField_5.getText().equals("") ||
							!EmpInterfaceDAO.checkNum(textField.getText())
							) {
						throw new notFoundExp();
					}
					else {
					
						Employee em = new Employee(
								Integer.parseInt(textField.getText()),
								textField_1.getText(),
								textField_2.getText(),
								String.valueOf(comboBox.getSelectedItem()),
								textField_4.getText(),
								textField_5.getText()
								);
						
								
							if (EmpInterfaceDAO.ajoutUser(em)) {
								JOptionPane.showMessageDialog(btnNewButton, "ajouté");
								wind.dispose();
								
							}
					}
				}catch(notFoundExp ne) {
					JOptionPane.showMessageDialog(null, "champs vide!", null, JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(384, 398, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("fermer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(493, 398, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("password");
		lblNewLabel_5_1.setForeground(Color.WHITE);
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(99, 319, 77, 23);
		contentPane.add(lblNewLabel_5_1);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.BLACK);
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(208, 319, 115, 20);
		contentPane.add(textField_5);
		
		
		
	}
	
	public void show() {
		wind.setVisible(true);
	}
}

