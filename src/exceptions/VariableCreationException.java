package exceptions;

public class VariableCreationException extends Exception {
    /**
	 * Should never happen if the variable factory is coded correctly. But in the unlikely circumstance
	 * that the variable factory cannot parse a proposed variable's value's type, then this is thrown.
	 */
	private static final long serialVersionUID = 1L;
}

