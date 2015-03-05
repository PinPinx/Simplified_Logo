package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.DuplicateVariableException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import exceptions.VariableNotFoundException;
import exceptions.VariableWrongTypeException;
import javafx.beans.property.StringProperty;
import view.components.Observer;

public class VariablesCollection implements Observable {
	private List<Variable> myVariableList;
	private List<Observer> myObserverList;
	
	public VariablesCollection(){
		this.myVariableList = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}
	
	public Object getVariableValue(String varName){
		for(Variable v : myVariableList){
			if(v.getNameProperty().get().equals(varName)){
				return v.getValue();
			}
		}
		//variable not found
		try {
			addVariable(varName, "0");
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {}//not possible but throw new VariableNotFoundException(); TODO
		
		return getVariableValue(varName);
		
	}
	
	public void addVariable(String varName, String varValue) throws VariableCreationException, VariableCreationInvalidValueException{
		for(Variable var : myVariableList){
			if(var.getNameProperty().get().equals(varName)){
				try {
					var.setValue(varValue);
					notifyObservers();
					return;
				} catch (VariableWrongTypeException e) {
					try {
						deleteVariable(varName);
						break;
					} catch (VariableNotFoundException e1) {} //never happens TODO
					//addVariable(varName, varValue);
				}
			}
		}
		Variable newVar = VariableFactory.createVariable(varName, varValue);
		myVariableList.add(newVar);
		notifyObservers();
	}
	
	public void deleteVariable(String varName) throws VariableNotFoundException{
		for(Variable v : myVariableList){
			if(v.getNameProperty().get().equals(varName)){
				myVariableList.remove(v);
				notifyObservers();
				return;
			}
		}
		throw new VariableNotFoundException("Variable "+varName+" does not exist. As such, it cannot be deleted.");
	}
	

	@Override
	public void notifyObservers() {
		List<StringProperty> variableDisplayProperties = new ArrayList<>();
		List<StringProperty> variableNameProperties = new ArrayList<>();
		for(Variable v : myVariableList){
			variableDisplayProperties.add(v.getStringProperty());
			variableNameProperties.add(v.getNameProperty());
		}
		VariablesCollectionUpdate vcu = new VariablesCollectionUpdate(variableNameProperties, variableDisplayProperties);
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
	
}
