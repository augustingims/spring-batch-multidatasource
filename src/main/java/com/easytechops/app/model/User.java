package com.easytechops.app.model;

/**
 * Created by TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com> on 12/1/24
 *
 * @author : TECHAP KEMADJEU AUGUSTIN <augustingims@gmail.com>
 * @date : 12/1/24
 */
public class User {
	private Long id;
	private String nom;
	private String prenom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
