package exceptions;

public class VariableNotFoundException extends Exception {
    /**
	 * Thrown when trying to access a variable in the workspace that doesn't exist.
	 */
	public VariableNotFoundException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
