package model;

import java.util.ArrayList;
import java.util.List;

import view.components.Observer;

public abstract class VCObservable implements Observable{

	private List<Observer> myObserverList;
	
	public VCObservable(){
		myObserverList = new ArrayList<>();
	}
	
	protected abstract VariablesCollectionUpdate produceUpdate();
	
	public void notifyObservers() {
		VariablesCollectionUpdate vcu = produceUpdate();
		for(Observer o : myObserverList){
			o.update(vcu);
		}
	}

	public void addObserver(Observer o) {
		myObserverList.add(o);
	}

	public void removeObserver(Observer o) {
		myObserverList.remove(o);
	}
	
}
