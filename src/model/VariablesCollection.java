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
	
	public Object getVariableValue(String varName) throws VariableNotFoundException {
		for(Variable v : myVariableList){
			if(v.getNameProperty().get().equals(varName)){
				return v.getValue();
			}
		}
		//variable not found
		try {
			addVariable(varName, "0");
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {throw new VariableNotFoundException();}//not possible

		return getVariableValue(varName);
		
	}
	
	public void addVariable(String varName, String varValue) throws VariableCreationException, VariableCreationInvalidValueException{
		for(Variable var : myVariableList){
			if(var.getNameProperty().get().equals(varName)){
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
			if(v.getNameProperty().get().equals(varName)){
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
		List<StringProperty> variableDisplayProperties = new ArrayList<>();
		List<StringProperty> variableNameProperties = new ArrayList<>();
		for(Variable v : myVariableList){
			variableDisplayProperties.add(v.getStringProperty());
			variableNameProperties.add(v.getNameProperty());
		}
		for(Observer o : myObserverList){
			o.update(variableNameProperties, variableDisplayProperties);
		}
	}
	
}
