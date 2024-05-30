package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.metier.Article;
import com.metier.BonAchat;
import com.metier.Personne;

public class RespAchatDAO {
	
	private static boolean isExistBon(int inBon) {
		Connection cnx = Factory.getInst();
		
		String req = "select baID from bon_achat";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if (rs.getInt("baID") == inBon) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }

		return exist;
	}
	
	private static boolean isExistArt(int id) {
		
		Connection cnx = Factory.getInst();
		
		String req = "select articleID from article";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if (rs.getInt("articleID") == id) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }

		return exist;
	}
	
	private static boolean isExistFrn(int id) {
		
		Connection cnx = Factory.getInst();
		
		String req = "select fournisseurID from fournisseur";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if (rs.getInt("fournisseurID") == id) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }

		return exist;
	}

	public static boolean addFrn(Personne f) {
		
		Connection cnx = Factory.getInst();
		String req = "insert into fournisseur values (?, ?, ?, ?, ?)";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
		    ps.setInt(1, f.getId());
		    ps.setString(2, f.getNom());
		    ps.setString(3, f.getPrenom());
		    ps.setString(4, f.getAdresse());
		    ps.setInt(5, f.getTel());
		    ps.executeUpdate();
		    ps.close();
		    return true;
			
		}
		catch(SQLException s) {
			JOptionPane.showMessageDialog(null, s.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
		
		return false;
		
	}

	public static boolean delFrn(int id) {
		
		
		Connection cnx = Factory.getInst();
		
		
		boolean exist = isExistFrn(id);
		
		    try {
		    	
			    if (exist == false) throw new notFoundExp();
			        
			    else {
				    	
				    String req2 = "delete from fournisseur where fournisseurID = ?";	
					try {
					    PreparedStatement ps2;
						ps2 = cnx.prepareStatement(req2);
					    ps2.setInt(1, id);
					    ps2.executeUpdate();
					    
					    ps2.close(); 
					    return true;
					  }
					    
						catch(SQLException s) {
							s.printStackTrace();
						}
				    }
			   }
		    catch(notFoundExp n) {
		    	JOptionPane.showMessageDialog(null, "n'existe pas!");
		    }
					
		return false;
	}

	public static boolean addBonDachat(BonAchat b) {
		
		Connection cnx = Factory.getInst();
		
		try {
			
			if ( !isExistFrn(b.getIdFrn()) ) throw new notFoundExp();
		
		else {
				try {
					if( !isExistArt(b.getArticleID()) ) throw new notFoundExp();
					
					else {
						try {
							
							// remplir bon_achat
							String req = "insert into bon_achat values (?, ?, ?, ?)";
							
						    PreparedStatement ps;
							ps = cnx.prepareStatement(req);
						    ps.setInt(1, b.getId());
						    
						    Date date = Date.valueOf(b.getDate());
						    ps.setDate(2, date);
						    
						    ps.setString(3, b.getStatut());
						    ps.setInt(4, b.getIdFrn());
						    ps.executeUpdate();
						    ps.close();
						    
						    // get art id
						    String req2 = "select articleID from article where articleID = ?";
						    
						    PreparedStatement ps2;
						    ps2 = cnx.prepareStatement(req2);
						    ps2.setInt(1, b.getArticleID());
						    ResultSet rs = ps2.executeQuery();
						    rs.next();
						    
						    int artID = rs.getInt("articleID");
						    ps2.close();
						    
						    // remplir ligne_ba
						    String req3 = "insert into ligne_ba values (?, ?, ?)";
						    
						    PreparedStatement ps3;
							ps3 = cnx.prepareStatement(req3);
						    ps3.setInt(1, b.getId());
						    ps3.setInt(2, artID);
						    ps3.setInt(3, b.getQteBA());
						    ps3.executeUpdate();
						    ps3.close();
						    
						    return true;
							
						}
						catch(SQLException s) {
							JOptionPane.showMessageDialog(null, s.getMessage());
						}
					}
				}
				catch(notFoundExp f) {
					JOptionPane.showMessageDialog(null, "ajouter cet article apres passer le BA!");
				}
			
				
			}
		}catch( notFoundExp n) {
			JOptionPane.showMessageDialog(null, "fournisseurID est un cle etranger dans bon_achat!");
		}
		
		return false;
	}

	
	public static boolean suppBonDachat(int inBon) {
		Connection cnx = Factory.getInst();
		
		
		boolean exist = isExistBon(inBon);
		
		    try {
		    	
			    if (exist == false) throw new notFoundExp();
			        
			    else {
				    	
				    String req2 = "delete from bon_achat where baID = ?";	
					try {
					    PreparedStatement ps2;
						ps2 = cnx.prepareStatement(req2);
					    ps2.setInt(1, inBon);
					    ps2.executeUpdate();
					    
					    ps2.close(); 
					    return true;
					  }
					    
						catch(SQLException s) {
							s.printStackTrace();
						}
				    }
			   }
		    catch(notFoundExp n) {
		    	JOptionPane.showMessageDialog(null, "n'existe pas!");
		    }
					
		return false;
	}

	public static boolean ajoutArt(Article a) {

		Connection cnx = Factory.getInst();
		
		List<String> l = new ArrayList<>();
		
		
		String req2 = "select designation from article";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req2);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	l.add(rs.getString("designation"));
		    }
		    
		    ps.close();
		}

		
		  catch(SQLException e) {
			  JOptionPane.showMessageDialog(null, e.getMessage());
		  }
		
		try {
			if(l.contains(a.getDesg())) throw new notFoundExp();
			
			else {
				String req = "insert into article values (?, ?, ?, ?, ?)";
				

				try {
					
				    PreparedStatement ps;
					ps = cnx.prepareStatement(req);
				    ps.setInt(1, a.getId());
				    ps.setString(2, a.getDesg());
				    ps.setFloat(3, a.getPrix());
				    ps.setString(4, a.getCategorie());
				    ps.setInt(5, a.getStock());
				    ps.executeUpdate();
				    ps.close();
				    return true;
					
				}
				catch(SQLException s) {
					JOptionPane.showMessageDialog(null, s.getMessage());
				}
			}
			
		}
		catch(notFoundExp n) {
			JOptionPane.showMessageDialog(null, "article existe deja!");
		}
		
		
		return false;
	}

	public static boolean suppArt(int idArt) {
		Connection cnx = Factory.getInst();
		
		boolean exist = isExistArt(idArt);
		
		    try {
		    	
			    if (exist == false) throw new notFoundExp();
			        
			    else {
				    	
				    String req2 = "delete from article where articleID = ?";	
					try {
					    PreparedStatement ps2;
						ps2 = cnx.prepareStatement(req2);
					    ps2.setInt(1, idArt);
					    ps2.executeUpdate();
					    
					    ps2.close(); 
					    return true;
					  }
					    
						catch(SQLException s) {
							s.printStackTrace();
						}
				    }
			   }
		    catch(notFoundExp n) {
		    	JOptionPane.showMessageDialog(null, "n'existe pas!");
		    }
					
		return false;
	}

	public static List<String> getIDArts() {
		
		Connection cnx = Factory.getInst();
		
		List<String> l = new ArrayList<>();
		
		String req = "select articleID from article";
		
		
			try {
				
			    PreparedStatement ps;
				ps = cnx.prepareStatement(req);
				
			    ResultSet rs = ps.executeQuery();
			    
			    while (rs.next()) {
			    	
			    	l.add(String.valueOf(rs.getInt("articleID")));
			    	
			    }
			    
			    ps.close(); }
			    
			 
		
			
		  catch(SQLException e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		  }
			return l;
	}


	public static List<BonAchat> getALLBons() {
		Connection cnx = Factory.getInst();
		
		String req = "select b.baID,"
				+ "dateBA,"
				+ "statut,"
				+ "fournisseurID,"
				+ "articleID,"
				+ "qteBA"
				+ " from bon_achat b, ligne_ba l "
				+ "where l.baID = b.baID";
		
		List<BonAchat> l = new ArrayList<>();
		try {
	    PreparedStatement ps;
		ps = cnx.prepareStatement(req);
	    ResultSet rs = ps.executeQuery();

		    
		    while (rs.next()) {
		    	String date = String.valueOf(rs.getDate("dateBA"));
		    	BonAchat b = new BonAchat(
		    			rs.getInt("baID"),
		    			date,
		    			rs.getString("statut"),
		    			rs.getInt("fournisseurID"),
		    			rs.getInt("articleID"),
		    			rs.getInt("qteBA")
		    			);
		    	l.add(b);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }
		
		return l;
	}

	public static List<String> getIDFrns() {
		
		Connection cnx = Factory.getInst();
		
		List<String> l = new ArrayList<>();
		
		String req = "select fournisseurID from fournisseur";
		
		
			try {
				
			    PreparedStatement ps;
				ps = cnx.prepareStatement(req);
				
			    ResultSet rs = ps.executeQuery();
			    
			    while (rs.next()) {
			    	
			    	l.add(String.valueOf(rs.getInt("fournisseurID")));
			    	
			    }
			    
			    ps.close(); }
			    
			 
		
			
		  catch(SQLException e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		  }
			return l;	
	}

	public static List<String> getIDBons() {

		Connection cnx = Factory.getInst();
		
		List<String> l = new ArrayList<>();
		
		String req = "select baID from bon_achat";
		
		
			try {
				
			    PreparedStatement ps;
				ps = cnx.prepareStatement(req);
				
			    ResultSet rs = ps.executeQuery();
			    
			    while (rs.next()) {
			    	
			    	l.add(String.valueOf(rs.getInt("baID")));
			    	
			    }
			    
			    ps.close(); }
			    
			 
		
			
		  catch(SQLException e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		  }
			return l;
		
	}
}
