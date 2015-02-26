package exceptions;

public class CommandNameNotFoundException extends Exception {

	/**
	 * Thrown when user types command that is not found in the default commands.
	 */
	public CommandNameNotFoundException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;

}

