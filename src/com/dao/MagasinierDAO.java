package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.metier.Article;
import com.metier.BonAchat;

public class MagasinierDAO {
		
	public static void valideEntrerStk(int id) {
		Connection cnx = Factory.getInst();
		
		Map<Integer, Integer> map_art_qte = new LinkedHashMap<>();
		List<Integer> l_baID = new ArrayList<>();
		
		try {
			
			// get baID in list
			
			String req = "select baID from bon_achat where statut = 'non valide'";
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	l_baID.add(rs.getInt("baID"));
		    }
		    ps.close();
		    
		    // get articleID and sum(qteBA) per article
		    
		    String req3 = "select articleID, sum(qteBA) as sum_qte from ligne_ba  \r\n"
		    		+ "where baID in (select baID from bon_achat where statut = 'non valide')\r\n"
		    		+ "group by articleID";
		    
		    PreparedStatement ps3;
			ps3 = cnx.prepareStatement(req3);
		    ResultSet rs2 = ps3.executeQuery();
		    
		    while (rs2.next()) {
		    	map_art_qte.put(rs2.getInt("articleID"), rs2.getInt("sum_qte"));
		    }
		    
		    ps3.close();
		    
		    // update bon_achat statut
		    
		    String req2 = "update bon_achat set statut = 'valide' where baID = ?";
		    
		    PreparedStatement ps2;
			ps2 = cnx.prepareStatement(req2);
			

			ps2.setInt(1, id);
			ps2.executeUpdate();
		    ps2.close();
		    
		    // set new stock
		    
		    String req4 = "update article set stock = stock + ? where articleID = ?";
		    
		    PreparedStatement ps4;
			ps4 = cnx.prepareStatement(req4);
		    
			for (Map.Entry<Integer, Integer> m : map_art_qte.entrySet() ) {
				
				ps4.setInt(1, m.getValue());
				ps4.setInt(2, m.getKey());
				ps4.executeUpdate();
			
			}

		    ps4.close();
		    
			
		}
		catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	public static List<BonAchat> getNonValidStk() {
		
		Connection cnx = Factory.getInst();
		
		String req = "select * from bon_achat where statut = 'non valide'";
		
		List<BonAchat> l = new ArrayList<>();
		
		try {
	    PreparedStatement ps;
		ps = cnx.prepareStatement(req);
	    ResultSet rs = ps.executeQuery();

		    
		    while (rs.next()) {
		    	String date = String.valueOf(rs.getDate("dateBA"));
		    	BonAchat b = new BonAchat(rs.getInt("baID"),
		    			date,
		    			rs.getString("statut"),
		    			rs.getInt("fournisseurID"));
		    	l.add(b);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }
		
		return l;
	}
		


	public static List<Article> getAllProducts() {
		
		Connection cnx = Factory.getInst();
		String req = "select * from article";
		
		List<Article> l = new ArrayList<>();
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	 
		    	Article a = new Article(rs.getInt("articleID"),rs.getString("designation"),rs.getFloat("prix"),rs.getString("categorie"),rs.getInt("stock"));
		    	l.add(a);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		  
	  }
		
		return l;
	}


}
