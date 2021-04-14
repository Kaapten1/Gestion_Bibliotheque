package test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ConnectionFactory;
import dao.EmpruntArchiveDao;
import dao.EmpruntEnCoursDao;
import dao.EmpruntEnCoursDb;
import dao.ExemplairesDao;
import dao.PingJdbc;
import dao.UtilisateursDao;
import metier.EmpruntArchive;
import metier.EmpruntEnCours;
import metier.Exemplaire;


public class TestDeBase {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		
		
	

		String[] choix = {"Consulter un exemplaire", "Consulter un utilisateur", "Emprunter un livre", "Rendre un livre", "Disponibilité d'un exemplaire", "Archives", "Archives nominatives", "Emprunts en cours"};
		int option = JOptionPane.showOptionDialog(null, "Faites votre choix", "Bibliothèque", 0, JOptionPane.QUESTION_MESSAGE, null, choix, "Consulter un exemplaire");
		
		
		if(option == 0 ) {
			ExemplairesDao exemplaireJOption = new ExemplairesDao(PingJdbc.getConnectionByProperties());
			String c = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire à consulter (pour rappel : 1 à 8) : ","Consultation d'un livre", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(exemplaireJOption.findByKey(Integer.parseInt(c)));
		} else if(option == 1) {
			UtilisateursDao utilisateurJOption = new UtilisateursDao(PingJdbc.getConnectionByProperties());
			String d = JOptionPane.showInputDialog(null, "Entrez l'ID d'un utilisateur (pour rappel : Employé = 2,3,6 ou Adhérent = 1,4,5,7,8) : ","Consultation d'un utilisateur", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(utilisateurJOption.findByKey(Integer.parseInt(d)));
		} else if (option == 2) {
			UtilisateursDao utilisateurJOption = new UtilisateursDao(PingJdbc.getConnectionByProperties());
			ExemplairesDao exemplaireJOption = new ExemplairesDao(PingJdbc.getConnectionByProperties());
			String a = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (pour rappel : Employé = 2,3,6 ou Adhérent = 1,4,5,7,8) : ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
			String b = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire emprunté ( de 1 à 8 ): ","Réalisation d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
			EmpruntEnCoursDao empruntEnCoursDao = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
			System.out.println(empruntEnCoursDao.insertEmpruntEnCours(new EmpruntEnCours(utilisateurJOption.findByKey(Integer.parseInt(a)), exemplaireJOption.findByKey(Integer.parseInt(b)))));
		} else if (option == 3) {
			EmpruntEnCoursDao empruntEnCours = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
			String e = JOptionPane.showInputDialog(null, "Entrez l'ID de l'exemplaire à rendre ( de 1 à 8 ): ","Retour d'un emprunt", JOptionPane.INFORMATION_MESSAGE);
			empruntEnCours.removeEmpruntEnCours(Integer.parseInt(e));
			//EmpruntArchiveDao empruntArchive = new EmpruntArchiveDao(PingJdbc.getConnectionByProperties());
		} else if (option == 4) {
			ExemplairesDao exemplaireDispoJOption = new ExemplairesDao(PingJdbc.getConnectionByProperties());
			for(Exemplaire e : exemplaireDispoJOption.findAll())
			System.out.println(e);
		} else if (option == 5) {
			EmpruntArchiveDao empruntArchive = new EmpruntArchiveDao(PingJdbc.getConnectionByProperties());
			for(EmpruntArchive emp : empruntArchive.findAll())
			System.out.println(emp);
		} else if (option == 6) {
			EmpruntArchiveDao empruntArchive = new EmpruntArchiveDao(PingJdbc.getConnectionByProperties());
			String f = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (pour rappel : Employé = 2,3,6 ou Adhérent = 1,4,5,7,8) : ","Visualisation des archives", JOptionPane.INFORMATION_MESSAGE);
			for(EmpruntArchive emp1 : empruntArchive.findByKey(Integer.parseInt(f)))
			System.out.println(emp1);
		} else if (option == 7) {
			String g = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (pour rappel : Employé = 2,3,6 ou Adhérent = 1,4,5,7,8) : ","Visualisation des emprunts en cours", JOptionPane.INFORMATION_MESSAGE);
			EmpruntEnCoursDao eEc = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
			for(EmpruntEnCoursDb empCours: eEc.findByUtilisateur(Integer.parseInt(g)))
			System.out.println(empCours);
		} else if (option == 8) {
			String h = JOptionPane.showInputDialog(null, "Entrez l'ID de l'emprunteur (pour rappel : Employé = 2,3,6 ou Adhérent = 1,4,5,7,8) : ","Retards", JOptionPane.INFORMATION_MESSAGE);
			EmpruntEnCoursDao eEc1 = new EmpruntEnCoursDao(PingJdbc.getConnectionByProperties());
			

		}
		
		
	}

}
