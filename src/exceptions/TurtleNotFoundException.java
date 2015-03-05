package exceptions;

public class TurtleNotFoundException extends Exception {
	/**
	 * Thrown when trying to retrieve a turtle from a turtle collection that does not yet exist.
	 */
	public TurtleNotFoundException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
	
}
