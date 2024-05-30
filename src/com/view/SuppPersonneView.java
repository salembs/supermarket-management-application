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
import javax.swing.border.EmptyBorder;

import com.dao.RespAchatDAO;
import com.dao.RespVenteDAO;
import com.dao.notFoundExp;

public class SuppPersonneView {

	private JFrame wind;
	private int id;
	private String[] result;
	private JPanel contentPane;

	public SuppPersonneView(String user) {
		initialize(user);
	}
	
	public void initialize(String user) {
		
		wind = new JFrame();
		wind.setTitle("supprimer");
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 571, 292);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		wind.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selectionner ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(183, 22, 183, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Id :");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(69, 111, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		
		// loading data
		List<String> l;
		
		if(user.equals("client")) {
			l = RespVenteDAO.getIDClients();
			result = l.toArray(new String[l.size()]);
		}
		if(user.equals("fournisseur")) {
			l = RespVenteDAO.getIDFrn();
			result = l.toArray(new String[l.size()]);
		}
		if(user.equals("commande")) {
			l = RespVenteDAO.getIDComs();
			result = l.toArray(new String[l.size()]);
		}
		if(user.equals("article")) {
			l = RespAchatDAO.getIDArts();
			result = l.toArray(new String[l.size()]);
		}
		if(user.equals("bonachat")) {
			l = RespAchatDAO.getIDBons();
			result = l.toArray(new String[l.size()]);
		}
		
		JComboBox comboBox = new JComboBox(result);
		comboBox.setBounds(148, 110, 121, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(comboBox.getItemCount() == 0) {
						throw new notFoundExp();
					}
				else {
						id = Integer.parseInt(String.valueOf(comboBox.getSelectedItem()));
						
						if(user.equals("client")) {
							
							if ( RespVenteDAO.suppClient(id) ) {
								JOptionPane.showMessageDialog(btnNewButton, "supprimé");
								wind.dispose();
							}
						}
						
						if(user.equals("fournisseur")) {
							
							
							if ( RespAchatDAO.delFrn(id) ) {
								JOptionPane.showMessageDialog(btnNewButton, "supprimé");
								wind.dispose();
							}
						}
						
						if(user.equals("commande")) {
							
							
							if ( RespVenteDAO.annulerCom(id) ) {
								JOptionPane.showMessageDialog(btnNewButton, "supprimé");
								wind.dispose();
							}
						}
						if(user.equals("article")) {
							
							if ( RespAchatDAO.suppArt(id) ) {
								JOptionPane.showMessageDialog(btnNewButton, "supprimé");
								wind.dispose();
								
							}
							
						}
						if(user.equals("bonachat")) {
							if ( RespAchatDAO.suppBonDachat(id) ) {
								JOptionPane.showMessageDialog(btnNewButton, "supprimé");
								wind.dispose();
								
							}
						}
					}
				}catch(notFoundExp ne) {
					JOptionPane.showMessageDialog(null, "champ vide!", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(232, 195, 107, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Fermer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wind.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(349, 195, 107, 23);
		contentPane.add(btnNewButton_1);
		
	}
	
	public void show() {
		wind.setVisible(true);
	}
}
