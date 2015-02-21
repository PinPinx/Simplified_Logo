package model;

public abstract class Variable {
	private String myName;

	/**
	 * Returns a nondestructible value.
	 */
	abstract public Object getValue();
	
	public String getName(){
		return this.myName;
	}
	
}
