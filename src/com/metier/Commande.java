package com.metier;

public class Commande {
	
	private int id;
	private int idArt;
	private String datecmd;
	private String status;
	private int clientid;
	private int qte;
	private float prix; // calculable
	
	
	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}



	public Commande(int id, String datecmd, String status, int clientid, int qte, int idArt) {
		this.id = id;
		this.status = status;
		this.datecmd = datecmd;
		this.clientid = clientid; // cle etranger nb
		this.qte = qte;
		this.idArt = idArt; // // cle etranger nb
	}
	
	
	
	public int getIdArt() {
		return idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}



	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDatecmd() {
		return datecmd;
	}
	public void setDatecmd(String datecmd) {
		this.datecmd = datecmd;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Commande [id=" + id + ", idArt=" + idArt + ", datecmd=" + datecmd + ", status=" + status + ", clientid="
				+ clientid + ", qte=" + qte + ", prix=" + prix + "]";
	}


	
	

}
