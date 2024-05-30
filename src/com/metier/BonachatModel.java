package com.metier;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


	public class BonachatModel extends AbstractTableModel {
		
		private String[] nomColonnes = new String[] {"Id","Date bon d'achat","statut","Id fournisseur"};
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
		public String getValueAt(int rowIndex, int columnIndex) {
			return rows.get(rowIndex)[columnIndex];
		}
		
		public String getColumnName(int column)
		{
			return nomColonnes[column];
		}
		
	    public void loadData(List<BonAchat> bons)
	    {
	    	rows = new Vector<String[]>();
	    	for(BonAchat a : bons)
	    	{
	    		rows.add(new String[] {
	    				String.valueOf(a.getId()),
	    				a.getDate(),
	    				a.getStatut(),
	    				String.valueOf(a.getIdFrn()),
	    				});
	    	}
	    	fireTableChanged(null);
	    }
    
}

