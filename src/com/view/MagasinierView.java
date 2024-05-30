package com.view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.dao.EmpInterfaceDAO;
import com.metier.Employee;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MagasinierView {

	private JFrame wind;
	private JPanel contentPane;

	public MagasinierView(String user, String pass) {
		initialize(user, pass);
	}
	
	public void initialize(String user, String pass) {
		
		wind = new JFrame();
		wind.setTitle("magasinier");
		
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 650, 466);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("gerer stock");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GererStkView bv = new GererStkView();
				bv.show();
			}
		});
		btnNewButton.setBackground(new Color(255, 102, 102));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(394, 71, 194, 53);
		contentPane.add(btnNewButton);
		btnNewButton.setBorderPainted(false);
		
		JButton btnNewButton_1 = new JButton("visualiser articles");
		btnNewButton_1.setBackground(new Color(255, 102, 102));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBorderPainted(false);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArticleView av = new ArticleView();
				av.show();
			}
		});
		
		
		btnNewButton_1.setBounds(394, 154, 194, 53);
		contentPane.add(btnNewButton_1);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 255, 255));
		panel.setBounds(10, 11, 260, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id employee :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 88, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nom :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 39, 75, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("prenom :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 64, 75, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("position :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 87, 75, 31);
		panel.add(lblNewLabel_3);
		
		Employee e = EmpInterfaceDAO.getEmployeeDB(user, pass);
		
		JLabel lblGgg = new JLabel(String.valueOf(e.getId()));
		lblGgg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGgg.setBounds(108, 11, 88, 28);
		panel.add(lblGgg);
		
		JLabel lblNewLabel_1_1 = new JLabel(e.getNom());
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(108, 39, 75, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel(e.getPrenom());
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(108, 64, 75, 25);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel(e.getPosition());
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(108, 87, 75, 31);
		panel.add(lblNewLabel_3_1);
		
	}
	
	public void show() {
		wind.setLocationRelativeTo(null);
		wind.setVisible(true);
	}
}

