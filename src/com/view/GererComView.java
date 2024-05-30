package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.dao.RespVenteDAO;
import com.metier.ComModel;
import com.metier.Commande;

public class GererComView  {

	private JPanel contentPane;
	private JTable table;
	private JFrame wind;
	private ComModel comModel;

	public GererComView() {
		initialize();
	}
	
	public void initialize() {
		
		wind = new JFrame();
		wind.setTitle("gerer commande");
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 814, 597);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("passer");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutComView cm = new AjoutComView();
				cm.show();
			}
		});
		btnNewButton.setBounds(658, 40, 99, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("annuler");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuppPersonneView s = new SuppPersonneView("commande");
				s.show();
			}
		});
		btnNewButton_1.setBounds(658, 92, 99, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("fermer");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				wind.dispose();
			}
		});
		btnNewButton_2.setBounds(351, 470, 99, 33);
		contentPane.add(btnNewButton_2);
		
		JPanel panel_frame = new JPanel();
		panel_frame.setBackground(Color.LIGHT_GRAY);
		panel_frame.setBounds(10, 11, 600, 384);
		contentPane.add(panel_frame);
		comModel = new ComModel();
        panel_frame.setLayout(null);
        table =new JTable(comModel);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setViewportBorder(null);
        jScrollPane.setBounds(0, 35, 600, 349);
        panel_frame.add(jScrollPane);
        
        JLabel lblNewLabel = new JLabel("Commandes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(209, 10, 82, 14);
        panel_frame.add(lblNewLabel);

		List<Commande> cmds = RespVenteDAO.getAllCmd();
		comModel.loadData(cmds);
		
	}
	
	public void show() {
		
		wind.setVisible(true);
	}
}
