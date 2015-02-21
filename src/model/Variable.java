package model;

public abstract class Variable {
	private String myName;
	
	public Variable(String name){
		this.myName = name;
	}
	
	/**
	 * Returns a nondestructible value.
	 */
	abstract public Object getValue();
	
	public String getName(){
		return this.myName;
	}

}
