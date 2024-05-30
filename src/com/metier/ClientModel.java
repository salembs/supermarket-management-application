package com.metier;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class ClientModel extends AbstractTableModel 
{
	private String[] nomColonnes = new String[] {"Id","Nom","Prenom","Adresse","Tel"};
	private Vector<String[]> rows = new Vector<String[]>();
	
	@Override
	public int getColumnCount() {
		return nomColonnes.length;
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows.get(rowIndex)[columnIndex];
	}
	
	public String getColumnName(int column)
	{
		return nomColonnes[column];
	}
	
    public void loadData(List<Personne> clt)
    {
    	rows = new Vector<String[]>();
    	for(Personne a : clt)
    	{
    		rows.add(new String[] {
    				String.valueOf(a.getId()),
    				a.getNom(),
    				a.getPrenom(),
    				a.getAdresse(),
    				String.valueOf(a.getTel()),
    				});
    	}
    	fireTableChanged(null);
    }
    
}
