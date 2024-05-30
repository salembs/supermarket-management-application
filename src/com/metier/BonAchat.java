package com.metier;

public class BonAchat {
	
	private int id;
	private String date;
	private int idFrn;  // cle etranger nb
	private String statut;
	private int articleID;  // cle etranger nb
	private int qteBA;  // cle etranger nb
	
	
	public BonAchat(int id, String date, String statut, int idFrn) {
		this.id = id;
		this.date = date;
		this.idFrn = idFrn;
		this.statut = statut;
	}
	
	public BonAchat(int id, String date, String statut, int idFrn,int articleID,int qteBA) {
		this.id = id;
		this.date = date;
		this.idFrn = idFrn;
		this.statut = statut;
		this.articleID = articleID;
		this.qteBA = qteBA;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdFrn() {
		return idFrn;
	}
	public void setIdFrn(int idFrn) {
		this.idFrn = idFrn;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getArticleID() {
		return articleID;
	}

	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}

	public int getQteBA() {
		return qteBA;
	}

	public void setQteBA(int qteBA) {
		this.qteBA = qteBA;
	}
	
	
	
	

}
