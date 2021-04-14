package metier;

public class TypeDonneesAnnee {
	
	int donneesAnnees;

	public TypeDonneesAnnee(int donneesAnnees) {
		super();
		this.donneesAnnees = donneesAnnees;
	}

	@Override
	public String toString() {
		return "TypeDonneesAnnee [donneesAnnees=" + donneesAnnees + "]";
	}

	public int getDonneesAnnees() {
		return donneesAnnees;
	}

	public void setDonneesAnnees(int donneesAnnees) {
		if(donneesAnnees >= 0) {
		this.donneesAnnees = donneesAnnees;
	
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
