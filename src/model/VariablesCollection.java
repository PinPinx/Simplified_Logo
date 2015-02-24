package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import view.Observer;
import Exceptions.DuplicateVariableException;
import Exceptions.VariableCreationException;
import Exceptions.VariableCreationInvalidValueException;
import Exceptions.VariableNotFoundException;
import Exceptions.VariableWrongTypeException;

public class VariablesCollection implements Observable {
	private List<Variable> myVariableList;
	private List<Observer> myObserverList;
	
	public VariablesCollection(){
		this.myVariableList = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}
	
	public Object getVariableValue(String varName) {
		for(Variable v : myVariableList){
			if(v.getName().equals(varName)){
				return v.getValue();
			}
		}
		//variable not found
		try {
			addVariable(varName, "0");
		} catch (DuplicateVariableException | VariableCreationException
				| VariableCreationInvalidValueException e) {}//not possible
		
		return getVariableValue(varName);
		
	}
	
	public void addVariable(String varName, String varValue) throws DuplicateVariableException, VariableCreationException, VariableCreationInvalidValueException{
		for(Variable var : myVariableList){
			if(var.getName().equals(varName)){
				try {
					var.setValue(varValue);
					return;
				} catch (VariableWrongTypeException e) {
					try {
						deleteVariable(varName);
					} catch (VariableNotFoundException e1) {} //never happens
					addVariable(varName, varValue);
				}
			}
		}
		Variable newVar = VariableFactory.createVariable(varName, varValue);
		myVariableList.add(newVar);
	}
	
	public void deleteVariable(String varName) throws VariableNotFoundException{
		for(Variable v : myVariableList){
			if(v.getName().equals(varName)){
				myVariableList.remove(v);
				return;
			}
		}
		throw new VariableNotFoundException();
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
	public void notifyObservers() {
		ArrayList<StringProperty> variableDisplayProperties = new ArrayList<>();
		for(Variable v : myVariableList){
			variableDisplayProperties.add(v.getStringProperty());
		}
		for(Observer o : myObserverList){
			o.update(variableDisplayProperties);
		}
	}
	
}
