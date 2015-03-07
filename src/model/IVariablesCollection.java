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
	/*public abstract void deleteVariable(String varName)
			throws VariableNotFoundException;*/

}