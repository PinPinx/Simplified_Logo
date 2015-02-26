package exceptions;

public class BadArgumentException extends Exception{
	/**
	 * Thrown when acting on a node that has been given incompatible arguments.
	 */
	public BadArgumentException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
}
