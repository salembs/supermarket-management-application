package com.metier;

public class Article {
	
	private int id;
	private String desg;
	private float prix;
	private String categorie;
	private int stock;
	
	public Article(int id, String desg, float prix, String categorie, int stock) {
		this.id = id;
		this.desg = desg;
		this.prix = prix;
		this.categorie = categorie;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesg() {
		return desg;
	}

	public void setDesg(String desg) {
		this.desg = desg;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	// just for testing
	public String toString() {
		return "Article [id=" + id + ", desg=" + desg + ", prix=" + prix + ", categorie=" + categorie + ", stock="
				+ stock + "]";
	}
	
	
	
	
}
