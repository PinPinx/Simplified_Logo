package model;

import Exceptions.VariableWrongTypeException;
import javafx.beans.property.StringProperty;

public abstract class Variable {
	private String myName;
	protected StringProperty myDisplayProperty;
	
	public Variable(String name){
		this.myName = name;
	}
	
	/**
	 * Returns a nondestructible value.
	 */
	abstract public Object getValue();
	
	/**
	 * This method is used to attempt to change the value (but NOT type) of the variable to a different value, edit.
	 * @throws VariableWrongTypeException if edit is not of the same type as the variable's value
	 */
	abstract public void setValue(String edit) throws VariableWrongTypeException;
	
	/**
	 * Returns a string of the variable's value. This String is binded to the Variable's
	 * real property, and this StringProperty is handed to the front end.
	 */
	protected StringProperty getStringProperty() {
		return myDisplayProperty;
	}
	
	public String getName(){
		return new String(this.myName);
	}

}
