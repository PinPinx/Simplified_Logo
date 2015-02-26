package exceptions;

public class DuplicateVariableException extends Exception {
	/**
	 * Thrown when attempting to add a new variable whose name already exists
	 * in the VariableCollection.
	 */
	public DuplicateVariableException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
	
}
