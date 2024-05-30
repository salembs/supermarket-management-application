package com.metier;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class ArticleModel extends AbstractTableModel 
{
	private String[] nomColonnes = new String[] {"Id","Designation","Prix","Categorie","Stock"};
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
	
    public void loadData(List<Article> arts)
    {
    	rows = new Vector<String[]>();
    	for(Article a : arts)
    	{
    		rows.add(new String[] {
    				String.valueOf(a.getId()),
    				a.getDesg(),
    				String.valueOf(a.getPrix()),
    				a.getCategorie(),
    				String.valueOf(a.getStock()),
    				});
    	}
    	fireTableChanged(null);
    }
    
}
