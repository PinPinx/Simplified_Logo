package exceptions;

public class VariableCreationInvalidValueException extends Exception {
    /**
	 * Thrown if attempting to create a variable with an invalid value, like :a = ]. ("]" is reserved
	 * for our SLogo language.
	 */
	public VariableCreationInvalidValueException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}

