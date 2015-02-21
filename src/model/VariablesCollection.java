package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;
import Exceptions.DuplicateVariableException;
import Exceptions.VariableNotFoundException;

public class VariablesCollection implements Observable {
	private List<Variable> myVariableList;
	private List<Observer> myObserverList;
	
	public VariablesCollection(){
		this.myVariableList = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}
	
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

	@Override
	public void addObserver(Observer o) {
		this.myObserverList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.myObserverList.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : this.myObserverList){
			o.update(getVariablesCollection());
		}
	}
	
	public List<Variable> getVariablesCollection(){
		return new ArrayList<>(this.myVariableList);
	}
}
