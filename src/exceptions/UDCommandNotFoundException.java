package exceptions;

public class UDCommandNotFoundException extends Exception {
	/**
	 * Thrown when searching for a specific UD command that does not exist in the current 
	 * UD command bank.
	 */
	public UDCommandNotFoundException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
	
}
