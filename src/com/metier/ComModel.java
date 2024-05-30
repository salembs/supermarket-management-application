package com.metier;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class ComModel extends AbstractTableModel 
{
	private String[] nomColonnes = new String[] {"Id","date","statut","client id","article id","qte","prix"};
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
	
    public void loadData(List<Commande> clt)
    {

    	rows = new Vector<String[]>();
    	for(Commande a : clt)
    	{
    		rows.add(new String[] {
    				String.valueOf(a.getId()),
    				a.getDatecmd(),
    				a.getStatus(),
    				String.valueOf(a.getClientid()),
    				String.valueOf(a.getQte()),
    				String.valueOf(a.getIdArt()),
    				String.valueOf(a.getPrix()),
    				});
    	}
    	fireTableChanged(null);
    }
    
}
