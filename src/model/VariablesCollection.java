package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import view.Components.VariablesObserver;
import Exceptions.DuplicateVariableException;
import Exceptions.VariableCreationException;
import Exceptions.VariableCreationInvalidValueException;
import Exceptions.VariableNotFoundException;
import Exceptions.VariableWrongTypeException;

public class VariablesCollection implements ObservableVariables {
	private List<Variable> myVariableList;
	private List<VariablesObserver> myObserverList;
	
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
					} catch (VariableNotFoundException e1) {} //never happens TODO
					addVariable(varName, varValue);
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
		throw new VariableNotFoundException();
	}
	

	@Override
	public void notifyObservers() {
		List<StringProperty> variableDisplayProperties = new ArrayList<>();
		List<StringProperty> variableNameProperties = new ArrayList<>();
		for(Variable v : myVariableList){
			variableDisplayProperties.add(v.getStringProperty());
			variableNameProperties.add(v.getNameProperty());
		}
		for(VariablesObserver o : myObserverList){
			o.update(variableNameProperties, variableDisplayProperties);
		}
	}

	@Override
	public void addObserver(VariablesObserver o) {
		// TODO Auto-generated method stub
		myObserverList.add(o);
	}

	@Override
	public void removeObserver(VariablesObserver o) {
		// TODO Auto-generated method stub
		myObserverList.remove(o);
	}
	
}
