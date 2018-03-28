package com.sc.gestionbddsqlite;

public class Magasin {

	private int id;
	private String nom;
	private String adresse;
	
	public Magasin() {}
	
	public Magasin(String nom, String adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String name) {
		this.nom = name;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String desc) {
		this.adresse = desc;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nom du Magasin = " + nom + "\n" + "Adresse = " + adresse);
		return sb.toString();
	}
	
}
