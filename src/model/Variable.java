package model;

<<<<<<< HEAD
public interface Variable {
	
	public String getName();
	public String getValue();
=======
public abstract class Variable {
	private String myName;

	/**
	 * Returns a nondestructible value.
	 */
	abstract public Object getValue();
	
	public String getName(){
		return this.myName;
	}
>>>>>>> e222d69f40bd417baeda612dc09fbdca1d5786a9
	
}
