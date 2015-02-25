package model;

import view.Components.TurtleObserver;

public interface ObservableTurtle {

	public void addObserver(TurtleObserver o);
	public void removeObserver(TurtleObserver o);
	public void notifyObservers();

}
