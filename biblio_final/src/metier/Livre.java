package metier;

public class Livre {
	
	private String isbn;
	private String titre;
	private TypeDonneesAnnee anneeParution;
	private int nbPages;
	
	





	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	
	public Livre(String isbn, String titre, TypeDonneesAnnee anneeParution, int nbPages) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.anneeParution = anneeParution;
		this.nbPages = nbPages;
	}



	public Livre() {
		super();
	}



	@Override
	public String toString() {
		return "Livre [isbn=" + isbn + ", titre=" + titre + ", anneeParution=" + anneeParution + ", nbPages=" + nbPages
				+ "]";
	}



	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public TypeDonneesAnnee getAnneeParution() {
		return anneeParution;
	}
	public void setAnneeParution(TypeDonneesAnnee anneeParution) {
		this.anneeParution = anneeParution;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

}
