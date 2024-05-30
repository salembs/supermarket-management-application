package com.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.controller.MagasinierController;
import com.controller.respAchatController;
import com.controller.respVenteController;
import com.dao.EmpInterfaceDAO;
import com.dao.notFoundExp;
import com.metier.Employee;

public class LoginFrame {

	private JFrame wind;
	private JTextField textField;
	private JPasswordField passwordField;


	public LoginFrame() {
		
		initialize();
		
	}
	
	
	public void initialize() {
		wind = new JFrame();
		wind.setTitle("login");
		wind.setLocationRelativeTo(null);
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setBounds(100, 100, 671, 481);
		wind.setResizable(false);
		
		JPanel panel = new JPanel();
		
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		wind.setContentPane(panel);
		panel.setBackground(Color.DARK_GRAY);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setBounds(251, 154, 142, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(251, 235, 142, 28);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(285, 63, 86, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("user");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(251, 129, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(251, 203, 86, 28);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("connect");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(434, 327, 106, 28);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("sign up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreerView c = new CreerView();
				c.show();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(318, 327, 106, 28);
		panel.add(btnNewButton_1);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			
			
            public void actionPerformed(ActionEvent e) {
            	
            	String usertxt = textField.getText();
            	String pass = new String(passwordField.getPassword());
            	textField.setText("");
				passwordField.setText("");
            	
            	
            	List<String> m = EmpInterfaceDAO.rechercheUser(usertxt, pass);

    			try {
    				
    				if(!m.get(0).equals("exist")) throw new notFoundExp();
    				
    				else {
    					
    					Employee emp = EmpInterfaceDAO.getEmployeeDB(usertxt, pass);
    					
    					
    					
    					switch(m.get(1)) {
    					
    						case "magasinier": 
    							
    							MagasinierView mv = new MagasinierView(usertxt, pass);
    							MagasinierController mc = new MagasinierController(emp, mv);
    							mc.visualiser();
    							break;
    							
    						case "resp vente":
    							
    							RespVenteView rv = new RespVenteView(usertxt, pass);
    							respVenteController rc = new respVenteController(emp, rv);
    							rc.visualiser();
    							break;
    							
    						case "resp achat": 
    							
    							RespAchatView av = new RespAchatView(usertxt, pass);
    							respAchatController ra = new respAchatController(emp, av);
    							ra.visualiser();
    							break;
    						
    					}
    				}
    				
    				wind.dispose();
    				
    			}catch(notFoundExp | IndexOutOfBoundsException er) {
    				
    				
    				JOptionPane.showMessageDialog(null, "Login Invalid!", null, JOptionPane.ERROR_MESSAGE);
    				
    			}
    			
    			
            }
        });
		
		

		
	}
	
	public void show() {
		wind.setLocationRelativeTo(null);
		wind.setVisible(true);
	}
}
