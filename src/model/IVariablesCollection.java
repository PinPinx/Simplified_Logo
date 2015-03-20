// This entire file is part of my masterpiece.
// Danny Oh

package model;

import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;

public interface IVariablesCollection extends Observable{

	public abstract Object getVariableValue(String varName);

	public abstract void addVariable(String varName, String varValue)
			throws VariableCreationException,
			VariableCreationInvalidValueException;
	
	/**
	 * Concatenates a String that contains all the necessary variable declarations to recreate
	 * the variable collection, for the purpose of saving.
	 */
	public String saveState();
	public void enterScope();
	public void exitScope();

}