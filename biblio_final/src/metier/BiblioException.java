package metier;

public class BiblioException extends Exception{

	/**
	 * Num�ro al�atoire
	 */
	private static final long serialVersionUID = 1L;

	public BiblioException() {
		super("Conditions d'autorisation d'un pr�t non remplie, v�rifiez vos droits.");
	}
	
	public BiblioException (String message) {
		super(message);
	}
	
	public static void main(String[] args) {
		System.out.println(new BiblioException());
		System.out.println(new BiblioException("livre non disponible"));
		}
	
}