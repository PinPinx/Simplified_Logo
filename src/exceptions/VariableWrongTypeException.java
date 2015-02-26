package exceptions;

public class VariableWrongTypeException extends Exception {
	/**
	 * Thrown when attempting to edit the value of a variable, and the proposed
	 * edit is not the same type as the variable.
	 */
	public VariableWrongTypeException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
   
}
