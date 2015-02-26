package model;

import view.components.TurtleObserver;

public interface ObservableTurtle {

	public void addObserver(TurtleObserver o);
	public void removeObserver(TurtleObserver o);
	public void notifyObservers();

}
