package model;

import java.util.List;

import Exceptions.DuplicateVariableException;
import Exceptions.VariableNotFoundException;

public class VariablesCollection {
	private List<Variable> myVariableList;
	
	public Object getVariableValue(String varName) throws VariableNotFoundException{
		for(Variable v : this.myVariableList){
			if(v.getName().equals(varName)){
				return v.getValue();
			}
		}
		throw new VariableNotFoundException();
	}
	
	public void addVariable(Variable v) throws DuplicateVariableException{
		for(Variable var : this.myVariableList){
			if(var.getName().equals(v.getName())){
				throw new DuplicateVariableException();
			}
		}
		this.myVariableList.add(v);
	}
	
	public void deleteVariable(String varName) throws VariableNotFoundException{
		for(Variable v : this.myVariableList){
			if(v.getName().equals(varName)){
				this.myVariableList.remove(v);
				return;
			}
		}
		throw new VariableNotFoundException();
	}
}
