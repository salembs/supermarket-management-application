package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.metier.Client;
import com.metier.Commande;
import com.metier.Fournisseur;
import com.metier.Personne;


public class RespVenteDAO {
	
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
		  e.printStackTrace();
	  }

		return exist;
	}

	public static boolean isExistClient(int id) {
		
	
		Connection cnx = Factory.getInst();
		
		String req = "select clientID from client";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if (rs.getInt("clientID") == id) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }

		return exist;
	}
	
	public static boolean isExistComd(int id) {
		
		
		Connection cnx = Factory.getInst();
		
		String req = "select clientID from commande";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if (rs.getInt("clientID") == id) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }

		return exist;
	}
	// if status = livré => can't cancel !
	public static boolean isExistCmd(int id) {

		
		Connection cnx = Factory.getInst();
		
		String req = "select commandeID, status from commande";
		
		boolean exist = false;
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	if ( (rs.getInt("commandeID") == id) && (rs.getString("status").equals("pret")) ) {
		    		exist = true;
		    	}
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }

		return exist;
	}
	
	public static boolean ajoutClient(Personne c) {
		
		Connection cnx = Factory.getInst();
		String req = "insert into client values (?, ?, ?, ?, ?)";
		
		if (!isExistClient(c.getId()) ) {
			
			try {
				
			    PreparedStatement ps;
				ps = cnx.prepareStatement(req);
			    ps.setInt(1, c.getId());
			    ps.setString(2, c.getNom());
			    ps.setString(3, c.getPrenom());
			    ps.setString(4, c.getAdresse());
			    ps.setInt(5, c.getTel());
			    ps.executeUpdate();
			    ps.close();
				return true;
			}
			catch(SQLException s) {
				s.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Id deja utilisé");
		}
		
		return false;
	}
		
	public static boolean suppClient(int id) {
		
			
			Connection cnx = Factory.getInst();
			
			
			boolean exist = isExistClient(id);
			
			    try {
			    	
				    if (exist == false) throw new notFoundExp();
				        
				    else {
					    	
					    String req2 = "delete from client where clientID = ?";	
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

	public static boolean passerCom(Commande c) {
		
		Connection cnx = Factory.getInst();
		String req = "insert into commande values (?, ?, ?, ?)";
		String req2 = "insert into ligne_cmd values (?, ?, ?, ?)";
		String req3 = "select prix from article where articleID = ?";
		
		try {
			
			if ( !isExistClient(c.getClientid()) ||  (!isExistArt(c.getIdArt())) || isExistComd(c.getId()) ) throw new notFoundExp();
		
		else {
			
				try {
					String req4 = "select stock from article where articleID = ?";
					PreparedStatement ps4;
				    ps4 = cnx.prepareStatement(req4);
				    ps4.setInt(1, c.getIdArt());
				    ResultSet rs2 = ps4.executeQuery();
				    rs2.next();
				    // get the new stock
				    
				    
				    if (rs2.getInt("stock") < c.getQte()) throw new notFoundExp();
				    
				    else {
				    	try {
				    		
				    		// update stock en article
				    		int newStock = rs2.getInt("stock") - c.getQte();
				    		String req5 = "update article set stock = ? where articleID = ?";
				    		PreparedStatement ps5;
							ps5 = cnx.prepareStatement(req5);
							ps5.setInt(1, newStock);
							ps5.setInt(2, c.getIdArt());
							ps5.executeUpdate();
							ps5.close();
							
							// table commande
						    PreparedStatement ps;
							ps = cnx.prepareStatement(req);
						    ps.setInt(1, c.getId());
						    
						    Date date = Date.valueOf(c.getDatecmd());
						    ps.setDate(2, date);

						    ps.setString(3, c.getStatus());
						    ps.setInt(4, c.getClientid());
						    ps.executeUpdate();
						    ps.close();
						    
						    // get prix
						    PreparedStatement ps3;
						    ps3 = cnx.prepareStatement(req3);
						    ps3.setInt(1, c.getIdArt());
						    ResultSet rs = ps3.executeQuery();
						    rs.next();
						    
						    float prixu = rs.getFloat("prix") * c.getQte();
						    
						    ps3.close();
						    
						    
						    // table ligne_cmd
						    PreparedStatement ps2;
							ps2 = cnx.prepareStatement(req2);
						    ps2.setInt(1, c.getId());
						    ps2.setInt(2, c.getIdArt());
						    ps2.setInt(3, c.getQte());
						    ps2.setFloat(4, prixu);
						    ps2.executeUpdate();
						    ps2.close();
						    
						    return true;
							
						}
						catch(SQLException s) {
							JOptionPane.showMessageDialog(null , s.getMessage());
						}
				    }
				    ps4.close();
				}
				catch(notFoundExp e) {
					JOptionPane.showMessageDialog(null , "stock insuffisant!");
				}
				catch(SQLException s) {
					JOptionPane.showMessageDialog(null , s.getMessage());
				}
				
			}
		}catch( notFoundExp n) {
			JOptionPane.showMessageDialog(null , "id existe ou client ou article est un cle etranger!");
		}
		
		return false;
		
	}
	
	public static boolean annulerCom(int idCom) {
		
		Connection cnx = Factory.getInst();
				
		boolean exist = isExistCmd(idCom);
		
		    try {
		    	
			    if (exist == false) throw new notFoundExp();
			        
			    else {
				    	
				    String req2 = "delete from commande where commandeID = ?";	
					try {
					    PreparedStatement ps2;
						ps2 = cnx.prepareStatement(req2);
					    ps2.setInt(1, idCom);
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
		    	JOptionPane.showMessageDialog(null, "id n'existe pas ou commande deja passé");
		    }
					
		return false;
		
	}

	public static List<Personne> getAllClient() {
		
		Connection cnx = Factory.getInst();
		String req = "select * from client";
		
		List<Personne> l = new ArrayList<>();
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	 
		    	Personne a = new Client(
		    			rs.getInt("clientID"),
		    			rs.getString("nom"),
		    			rs.getString("prenom"),
		    			rs.getString("adresse"),
		    			rs.getInt("telephone")
		    			);
		    	l.add(a);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		
		return l;
	}
	
	public static List<Commande> getAllCmd() {
		
		Connection cnx = Factory.getInst();
		String req = "select c.commandeID,"
				+ "dateCmd,"
				+ "status,"
				+ "clientID,"
				+ "articleID,"
				+ "qte,"
				+ "prixU"
				+ " from commande c, ligne_cmd l where l.commandeID = c.commandeID";
		
		List<Commande> l = new ArrayList<>();
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	// int id, String datecmd, String status, int clientid, int qte, int idArt
		    	Commande a = new Commande(
		    			rs.getInt("commandeID"),
		    			String.valueOf(rs.getDate("dateCmd")),
		    			rs.getString("status"),
		    			rs.getInt("clientID"),
		    			rs.getInt("qte"),
		    			rs.getInt("articleID"));
		    	
		    	a.setPrix(rs.getFloat("prixU"));
		    	l.add(a);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		
		return l;
	}

	public static List<String> getIDClients() {
	
	Connection cnx = Factory.getInst();
	
	List<String> l = new ArrayList<>();
	
	String req = "select clientID from client";
	
	
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	
		    	l.add(String.valueOf(rs.getInt("clientID")));
		    	
		    }
		    
		    ps.close(); }
		    
		 
	
		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		return l;
	}

	public static List<String> getIDFrn() {
	
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
		  e.printStackTrace();
	  }
		return l;
	}

	public static List<String> getIDComs(){
		
		Connection cnx = Factory.getInst();
		
		List<String> l = new ArrayList<>();
		
		String req = "select commandeID from commande";
		
		
			try {
				
			    PreparedStatement ps;
				ps = cnx.prepareStatement(req);
				
			    ResultSet rs = ps.executeQuery();
			    
			    while (rs.next()) {
			    	
			    	l.add(String.valueOf(rs.getInt("commandeID")));
			    	
			    }
			    
			    ps.close(); }
			    
			 
		
			
		  catch(SQLException e) {
			  e.printStackTrace();
		  }
			return l;
		
	}
	
	public static List<String> getDesgs() {
	Connection cnx = Factory.getInst();
	
	List<String> l = new ArrayList<>();
	
	String req = "select articleID, designation from article";
	
	
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	
		    	l.add(rs.getString("designation"));
		    	
		    }
		    
		    ps.close(); }
		    
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		return l;
}


	public static int getIDArticle(String desg) {

		Connection cnx = Factory.getInst();
		
		String req = "select articleID from article where designation = ?";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setString(1, desg);
			
		    ResultSet rs = ps.executeQuery();
		    
		    rs.next();
		    int id = rs.getInt("articleID");
		    ps.close();
		    return id;
		}
	
		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		
		return -1;
	
	}

	public static List<Personne> getAllFrn() {
		
		Connection cnx = Factory.getInst();
		String req = "select * from fournisseur";
		
		List<Personne> l = new ArrayList<>();
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	 
		    	Personne a = new Fournisseur(
		    			rs.getInt("fournisseurID"),
		    			rs.getString("nom"),
		    			rs.getString("prenom"),
		    			rs.getString("adresse"),
		    			rs.getInt("telephone")
		    			);
		    	l.add(a);
		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  e.printStackTrace();
	  }
		
		return l;
		
	}

		
	
}


		

