package model;


import view.Components.VariablesObserver;

public interface ObservableVariables {
	
	public void addObserver(VariablesObserver o);
	public void removeObserver(VariablesObserver o);
	public void notifyObservers();

}
