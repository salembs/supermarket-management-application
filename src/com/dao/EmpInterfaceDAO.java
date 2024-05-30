package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.metier.Article;
import com.metier.Employee;

public interface EmpInterfaceDAO {
	
	
	public static List<Article> rechercheParArticle(String nom){
		
		
		Connection cnx = Factory.getInst();
		String req = "select * from article where designation like ?";
		
		List<Article> l = new ArrayList<>();
		
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setString(1, nom + "%");	    
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
	
	public static List<Article> rechercheParCategorie(String cat){
		
		List<Article> l = new ArrayList<>();
		
		Connection cnx = Factory.getInst();
		String req = "select * from article where categorie like ?";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setString(1, cat + "%");
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

	public static List<String> rechercheUser(String user, String pass) {
		
		Connection cnx = Factory.getInst();
		
		List<String> m = new ArrayList<>();
		String req = "select user, pass, position from employee where user = ? and pass = ?";
		
		
		
		try {
			
			PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setString(1, user);
			ps.setString(2, pass);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	 
		    	if ( ( rs.getString("user").equals(user) ) && ( rs.getString("pass").equals(pass) ) ) {
		    		m.add("exist");
		    		m.add(rs.getString("position"));
		    		return m;
		    	}

		    }
		    
		    ps.close();
	    
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }
		
		return m;
		
	}

	public static Employee getEmployeeDB(String user, String pass) {
		
		Connection cnx = Factory.getInst();
		String req = "select * from employee where user = ? and pass = ?";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setString(1, user);
			ps.setString(2, pass);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	 
		    	if ( ( rs.getString("user").equals(user) ) && ( rs.getString("pass").equals(pass) ) ) {
		    		
		    		return new Employee(
		    				rs.getInt("employeeID"),
		    				rs.getString("nom"),
		    				rs.getString("prenom"),
		    				rs.getString("position"),
		    				rs.getString("user"),
		    				rs.getString("pass"));
		    		
		    	}

		    }
		    
		    ps.close();
		}

		
	  catch(SQLException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
		  
	  }
		
		return null;
	}
	
	
	public static boolean verifFormat(String date) {
		
		String datePattern = "\\d{4}-\\d{2}-\\d{2}";

	       return Pattern.matches(datePattern, date);

		
	}
	
	public static boolean verifHoleDate(String date) {
		
		if(verifFormat(date)) {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));
			return (year >= 2000 && year <= 2024) &&
					(month >= 1 && month <= 12) &&
					(day >= 1 && day <= 31);
		}
		else {
			return false;
		}
		
	}
	
	public static boolean ajoutUser(Employee e){
		
		Connection cnx = Factory.getInst();
		
		String req = "insert into employee values (?,?,?,?,?,?)";
		
		try {
			
		    PreparedStatement ps;
			ps = cnx.prepareStatement(req);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getNom());    
			ps.setString(3, e.getPrenom());    
			ps.setString(4, e.getPosition());    
			ps.setString(5, e.getLogin());    
			ps.setString(6, e.getPass());    
		    ps.executeUpdate();
		    ps.close();
		    return true;
		}

		
	  catch(SQLException ee) {
		  JOptionPane.showMessageDialog(null, ee.getMessage(), null, JOptionPane.ERROR_MESSAGE);
	  }
		
		return false;
		
	}
	
	
	public static boolean checkNum(String input) {
		
		try {
            int n = Integer.parseInt(input);
            return n >= 1;
        } catch (NumberFormatException e) {
    
            return false;
        }
	}
	
	public static boolean checkChaine(String input) {

		Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();

	}
	
}
  