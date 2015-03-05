package exceptions;

public class InvalidViewSettingException extends Exception {
	/**
	 * Thrown when attempting to modify a turtle's view setting's to an invalid configurations.
	 * Ex: Setting the palette of a turtle to rgb values outside the range [0,256).
	 */
	public InvalidViewSettingException(String message){
		super(message);
	}
	
	private static final long serialVersionUID = 1L;
	
}
