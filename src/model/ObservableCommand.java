package model;

import view.Components.CommandsObserver;


public interface ObservableCommand {
	
	public void addObserver(CommandsObserver o);
	public void removeObserver(CommandsObserver o);
	public void notifyObservers();


}
