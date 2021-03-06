package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import metier.Adherent;
import metier.Employe;
import metier.EmpruntArchive;
import metier.EmpruntEnCours;
import metier.EnumCategorieEmploye;
import metier.Livre;
import metier.Utilisateur;

public class EmpruntEnCoursDao {
	

	Connection cnx3 = null;
	public SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
	public EmpruntEnCoursDao(Connection cnx3) {
		this.cnx3=cnx3;
	}
	
	public boolean insertEmpruntEnCours( EmpruntEnCours emprunt ) throws SQLException {
		
		Statement stmt = cnx3.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT STATUS FROM EXEMPLAIRE WHERE idexemplaire ="+emprunt.getExemplaire().getIdExemplaire());
		rs.next();
		if (rs.getString(1).equalsIgnoreCase("DISPONIBLE")) {
			if(emprunt.getUtilisateur().getCategorieUtilisateur().equalsIgnoreCase("ADHERENT")) {
				ResultSet rs2 = stmt.executeQuery("SELECT dateemprunt FROM EMPRUNTENCOURS WHERE idutilisateur ="+emprunt.getUtilisateur().getidUtilisateur());
				int x=0;
				int y=0;
				while(rs2.next()) {
					long dif = ChronoUnit.DAYS.between(LocalDate.parse(rs2.getDate(1).toString()), LocalDate.now());
					if( (int) dif > 15 && (int) dif!=730485) x++;
					y++;
				}
				if( y<3 ) {							
							if ( x==0 ) {
									
									PreparedStatement pstmt = cnx3.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'))");
									pstmt.setInt(1,emprunt.getExemplaire().getIdExemplaire());
									pstmt.setInt(2,emprunt.getUtilisateur().getidUtilisateur());
									pstmt.setDate(3,Date.valueOf(emprunt.getDateEmprunt()));
									pstmt.executeUpdate();
									pstmt.close();
									PreparedStatement pstmt4 = cnx3.prepareStatement("UPDATE EXEMPLAIRE SET STATUS='PRETE' WHERE IDEXEMPLAIRE = ?");
									pstmt4.setInt(1,emprunt.getExemplaire().getIdExemplaire());
									pstmt4.executeUpdate();
									pstmt4.close();									
									rs.close();
									rs2.close();
									stmt.close();									
									//cnx3.close();
									return true;
							
							}else {
								
								JOptionPane.showMessageDialog(null, "D?sol? mais vous avez "+x+" exemplaire(s) en retard de restitution", "Quantit? d'exemplaire emprunt?s en retard", JOptionPane.WARNING_MESSAGE);
								rs.close();
								rs2.close();
								stmt.close();
								cnx3.close();
								return false;
							}
							
										
				}else {
										
					JOptionPane.showMessageDialog(null, "D?sol? mais vous ne pouvez pas empruntez plus de trois exemplaires", "Quantit? d'exemplaires emprunt?s", JOptionPane.WARNING_MESSAGE);
					rs.close();
					rs2.close();
					stmt.close();
					cnx3.close();
					return false;
				}
			}else {

				PreparedStatement pstmt = cnx3.prepareStatement("INSERT INTO EMPRUNTENCOURS VALUES (?, ?, TO_DATE(?, 'DD-MM-YYYY'))");
				pstmt.setInt(1,emprunt.getExemplaire().getIdExemplaire());
				pstmt.setInt(2,emprunt.getUtilisateur().getidUtilisateur());
				pstmt.setDate(3,Date.valueOf(emprunt.getDateEmprunt()));
				pstmt.executeUpdate();
				pstmt.close();
				PreparedStatement pstmt5 = cnx3.prepareStatement("UPDATE EXEMPLAIRE SET STATUS='PRETE' WHERE IDEXEMPLAIRE = ?");
				pstmt5.setInt(1,emprunt.getExemplaire().getIdExemplaire());
				pstmt5.executeUpdate();
				pstmt5.close();
				rs.close();
				stmt.close();				
				//cnx3.close();
				return true;
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "D?sol? mais l'exemplaire demand? est "+rs.getString(1), "Disponibilit? de l'exemplaire demand?", JOptionPane.WARNING_MESSAGE);
			rs.close();
			stmt.close();
			cnx3.close();
			return false;
		}
	}
	
	public void removeEmpruntEnCours(int idExemplaire) throws SQLException {
		
		Statement stmt = cnx3.createStatement();
		
		ResultSet rs8 = stmt.executeQuery("SELECT * FROM EMPRUNTENCOURS WHERE IDEXEMPLAIRE ="+ idExemplaire);
		if(rs8.next()) {
		
			Statement stmt2 = cnx3.createStatement();
			
			ResultSet rs3 = stmt2.executeQuery("SELECT IDEMPRUNTARCHIVE+1 FROM EMPRUNTARCHIVE ORDER BY IDEMPRUNTARCHIVE DESC FETCH FIRST 1 ROWS ONLY");
			rs3.next();
			
			
			PreparedStatement pstmt6 = cnx3.prepareStatement("INSERT INTO EMPRUNTARCHIVE VALUES (?, TO_DATE(?, 'DD-MM-YYYY'), TO_DATE(?, 'DD-MM-YYYY'),?,?)");
			pstmt6.setInt(1,(rs3.getInt(1)));
			pstmt6.setDate(2,rs8.getDate(3));
			pstmt6.setDate(3,Date.valueOf(LocalDate.now()));
			pstmt6.setInt(4,rs8.getInt(1));
			pstmt6.setInt(5,rs8.getInt(2));
			pstmt6.executeUpdate();
			pstmt6.close();
			rs8.close();
			rs3.close();
			stmt.close();
			stmt2.close();
			
			PreparedStatement pstmt2 = cnx3.prepareStatement("DELETE FROM EMPRUNTENCOURS WHERE IDEXEMPLAIRE = ?");
			pstmt2.setInt(1,idExemplaire);
			pstmt2.execute();
			pstmt2.close();
			PreparedStatement pstmt3 = cnx3.prepareStatement("UPDATE EXEMPLAIRE SET STATUS='DISPONIBLE' WHERE IDEXEMPLAIRE = ?");
			pstmt3.setInt(1,idExemplaire);
			pstmt3.executeUpdate();
			pstmt3.close();	
			}
		cnx3.close();
			

    }
	public EmpruntEnCoursDb findByKey(int idExemplaire) throws SQLException
	{
		Statement stmt3 = cnx3.createStatement();
		ResultSet rs2 = stmt3.executeQuery(
				"select idexemplaire,idUtilisateur "+
				" FROM exemplaire,utilisateur where idexemplaire = " + idExemplaire);			
		EmpruntEnCoursDb ex = null;
		
		boolean next = rs2.next();
		
		
		if( next ) {
			idExemplaire = rs2.getInt("idexemplaire"); 
			int idUtilisateur = rs2.getInt("idutilisateur"); 	
			ex = new EmpruntEnCoursDb(idUtilisateur, idExemplaire); 
		}
		else {
			ex = null;
		}
		
		
		return ex;
			
	}
	
	
	public List<EmpruntEnCoursDb> findByUtilisateur(int idutilisateur) throws SQLException {
		Statement stmt4 = cnx3.createStatement();
		List<EmpruntEnCoursDb> listeEmpruntEnCoursDb = new ArrayList<EmpruntEnCoursDb>();
		ResultSet rs4 = stmt4.executeQuery("select * FROM empruntencours where idutilisateur = " + idutilisateur);			
		while( rs4.next()){
			
			int idexemplaire = rs4.getInt(1);
			idutilisateur = rs4.getInt(2);

			EmpruntEnCoursDb ex2 = new EmpruntEnCoursDb(idutilisateur, idexemplaire);//mapping Objet Relationel
			listeEmpruntEnCoursDb.add(ex2);
			
		}
		
		return listeEmpruntEnCoursDb;
	}
	
	

	
	

	

}