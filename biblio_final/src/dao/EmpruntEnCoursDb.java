package dao;

import java.time.LocalDate;

import metier.Exemplaire;
import metier.Utilisateur;

public class EmpruntEnCoursDb {
	private int idUtilisateur;
	private int idExemplaire;
	
	
	
	
	public EmpruntEnCoursDb(int idUtilisateur, int idExemplaire) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idExemplaire = idExemplaire;
	}
	@Override
	public String toString() {
		return "EmpruntEnCoursDb [idUtilisateur=" + idUtilisateur + ", idExemplaire=" + idExemplaire + "]";
	}
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public int getIdExemplaire() {
		return idExemplaire;
	}
	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}
	


	
}