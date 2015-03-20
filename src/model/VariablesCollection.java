// This entire file is part of my masterpiece.
// Danny Oh

package model;

import java.util.ArrayList;
import java.util.List;

import parser.parser.Regex;
import exceptions.CommandNameNotFoundException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import exceptions.VariableNotFoundException;
import exceptions.VariableWrongTypeException;
import javafx.beans.property.StringProperty;
import view.components.Observer;

public class VariablesCollection implements IVariablesCollection{
	private List<Variable> myVariableList;
	private List<Variable> waitingVariableList;
	private List<Observer> myObserverList;
	
	public VariablesCollection(){
		this.myVariableList = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
		waitingVariableList = new ArrayList<>();
	}
	
	public boolean containsVariable(String varName){
		for(Variable v : myVariableList){
			if(v.getNameProperty().get().equals(varName)){
				return true;
			}
		}
		return false;
	}
		
	@Override
	public Object getVariableValue(String varName){
		try {
			Variable v = findVariable(varName);
			return v.getValue();
		} catch (VariableNotFoundException e){
			try {
				addVariable(varName, "0");
			} catch (VariableCreationException
					| VariableCreationInvalidValueException e1) {}
		}
		return getVariableValue(varName);
	}
	
	@Override
	public void addVariable(String varName, String varValue) throws VariableCreationException, VariableCreationInvalidValueException{
			try {
				Variable var = findVariable(varName);
				var.setValue(varValue);
				notifyObservers();
				return;
			} catch (VariableNotFoundException e) {
				Variable newVar = VariableFactory.createVariable(varName, varValue);
				myVariableList.add(newVar);
				notifyObservers();
			} catch (VariableWrongTypeException e) {
				throw new VariableCreationException("You're adding/setting a variable in the wrong way somehow.");
			}
			
	}
	
	public void waitScopeVariable(String varName){
		try{
			Variable var = findVariable(varName);
			myVariableList.remove(var);
			waitingVariableList.add(var);
		}
		catch (VariableNotFoundException e){}
	}
	
	public void restoreScope(){
		for (Variable var: waitingVariableList){
			myVariableList.add(var);
		}
		waitingVariableList.clear();
	}
	
	private Variable findVariable(String varName) throws VariableNotFoundException{
		for (Variable v : myVariableList){
			if(v.getNameProperty().get().equals(varName))
				return v;
		}
		throw new VariableNotFoundException("Variable " + varName + " does not exist.");
	}
	
	public VariablesCollectionUpdate produceUpdate(){
		List<StringProperty> variableDisplayProperties = new ArrayList<>();
		List<StringProperty> variableNameProperties = new ArrayList<>();
		for(Variable v : myVariableList){
			variableDisplayProperties.add(v.getStringProperty());
			variableNameProperties.add(v.getNameProperty());
		}
		return new VariablesCollectionUpdate(variableNameProperties, variableDisplayProperties);
	}
	
	@Override
	public void notifyObservers() {
		VariablesCollectionUpdate vcu = produceUpdate();
		for(Observer o : myObserverList){
			o.update(vcu);
		}
	}

	@Override
	public void addObserver(Observer o) {
		myObserverList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		myObserverList.remove(o);
	}

	@Override
	public void enterScope() {
		//Do nothing
	}

	@Override
	public void exitScope() {
		//Do nothing
	}

	@Override
	public String saveState() {
		StringBuilder b = new StringBuilder();
		String declare;
		try {
			declare = Regex.getInstance().getCommandString("makevariable");
		} catch (CommandNameNotFoundException e) {
			return "";
		}
		for(Variable v : myVariableList){
			b.append(declare);
			b.append(" ");
			b.append(v.getNameProperty().get());
			b.append(" ");
			b.append(v.getValue().toString());
			b.append("\n");
		}
		return b.toString();
	}
	
}
