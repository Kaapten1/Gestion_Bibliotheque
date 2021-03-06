package metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Personne 
{
   private String nom;
   private String prenom;
   protected static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   private LocalDate dateNaissance;
   private String sexe;
   
   /**
    * @roseuid 604209D101D5
    */
   public Personne(String nom, String prenom, String dateNaissance, String sexe) 
   {
    
	   this.nom = nom;
	   this.prenom = prenom;
	   setDateNaissance(dateNaissance);
	   this.sexe = sexe;
   }
   
   public Personne (){
		//this("nom inconnu", "prenom inconnu",  "dateNaissance", "Sexe inconnu");
	}
	
	
	
	public LocalDate getDateNaissance() {
	return dateNaissance;
}

public LocalDate setDateNaissance(String dateNaissance) {
	return LocalDate.parse(dateNaissance);
}

public String getSexe() {
	return sexe;
}

public void setSexe(String sexe) {
	this.sexe = sexe;
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



	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", pr?nom=" + prenom + ", sexe =" + sexe + "]";
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
