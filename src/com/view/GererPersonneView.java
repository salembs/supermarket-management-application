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
import com.metier.ClientModel;
import com.metier.Personne;

public class GererPersonneView  {

	private JPanel contentPane;
	private JTable table;
	private JFrame wind;
	private ClientModel cltModel;

	public GererPersonneView(String user) {
		initialize(user);
	}
	
	public void initialize(String user) {
		
		wind = new JFrame();
		wind.setTitle("gerer personne");
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 717, 541);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("ajouter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjoutPersonneView p = new AjoutPersonneView(user);
				p.show();
			}
		});
		btnNewButton.setBounds(566, 39, 99, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("supprimer");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SuppPersonneView s = new SuppPersonneView(user);
				s.show();
			}
		});
		btnNewButton_1.setBounds(566, 91, 99, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("fermer");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
			}
		});
		btnNewButton_2.setBounds(287, 442, 99, 33);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("modifier");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifPersonneView md = new ModifPersonneView(user);
				md.show();
			}
		});
		btnNewButton_3.setBounds(566, 144, 99, 33);
		contentPane.add(btnNewButton_3);
		
		JPanel panel_frame = new JPanel();
		panel_frame.setBackground(Color.LIGHT_GRAY);
		panel_frame.setBounds(10, 11, 505, 384);
		contentPane.add(panel_frame);
        cltModel = new ClientModel();
        panel_frame.setLayout(null);
        table =new JTable(cltModel);

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setViewportBorder(null);
        jScrollPane.setBounds(0, 35, 505, 349);
        panel_frame.add(jScrollPane);
        
        JLabel lblNewLabel = new JLabel(user + "s");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(208, 10, 94, 14);
        panel_frame.add(lblNewLabel);
		
        if(user.equals("client")) {
			List<Personne> pers = RespVenteDAO.getAllClient();
			cltModel.loadData(pers);
        }
        
        if(user.equals("fournisseur")) {
			List<Personne> pers = RespVenteDAO.getAllFrn();
			cltModel.loadData(pers);
        }
        
		
	}
	
	public void show() {
		wind.setVisible(true);
	}
}
