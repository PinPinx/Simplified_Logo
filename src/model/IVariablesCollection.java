package model;

import view.components.Observer;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import exceptions.VariableNotFoundException;

public interface IVariablesCollection extends Observable{

	public abstract Object getVariableValue(String varName);

	public abstract void addVariable(String varName, String varValue)
			throws VariableCreationException,
			VariableCreationInvalidValueException;

	public void addObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
	
	/**
	 * Concatenates a String that contains all the necessary variable declarations to recreate
	 * the variable collection, for the purpose of saving.
	 */
	public String saveState();
	public void enterScope();
	public void exitScope();

}