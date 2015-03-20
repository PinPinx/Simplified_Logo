// This entire file is part of my masterpiece.
// Danny Oh

package model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;


public class ScopedVariablesCollection extends VCObservable implements IVariablesCollection{

	private List<VariablesCollection> collectionStack;
	
	public ScopedVariablesCollection(){
		collectionStack = new ArrayList<VariablesCollection>();
		collectionStack.add(new VariablesCollection());
	}
	
	public void enterScope(){
		collectionStack.add(0, new VariablesCollection());
	}
	
	public void exitScope(){
		collectionStack.remove(0);
		collectionStack.get(0).restoreScope();
		notifyObservers();
	}
	
	public boolean containsVariable(String varName){
		for(VariablesCollection collection: collectionStack){
			if (collection.containsVariable(varName))
				return true;
		}
		return false;
	}
		
	public Object getVariableValue(String varName){
		for (VariablesCollection collection: collectionStack){
			if (collection.containsVariable(varName)){
				return collection.getVariableValue(varName);
			}
		}
		try {
			addVariable(varName, "0");
			notifyObservers();
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {}
		return getVariableValue(varName);
	}
	
	public void addVariable(String varName, String varValue) throws VariableCreationException, VariableCreationInvalidValueException{
		for (int i = 1; i < collectionStack.size(); i++){
			collectionStack.get(i).waitScopeVariable(varName);
		}
		collectionStack.get(0).addVariable(varName, varValue);
		notifyObservers();
	}

	protected VariablesCollectionUpdate produceUpdate(){
		List<StringProperty> variableDisplayProperties = new ArrayList<>();
		List<StringProperty> variableNameProperties = new ArrayList<>();
		for (VariablesCollection collection: collectionStack){
			VariablesCollectionUpdate update = collection.produceUpdate();
			variableDisplayProperties.addAll(update.getDisplayProperties());
			variableNameProperties.addAll(update.getNameProperties());
		}
		return new VariablesCollectionUpdate(variableNameProperties, variableDisplayProperties);
	}

	@Override
	public String saveState() {
		return collectionStack.get(0).saveState();
	}
}
