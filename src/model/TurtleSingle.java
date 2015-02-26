package model;

import java.util.ArrayList;
import java.util.List;


import view.Components.TurtleObserver;

public class TurtleSingle implements Turtle {
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isActive, isClear;
	private List<TurtleObserver> myObservers;
	
	public TurtleSingle(){
		myCoordinates = new Coordinates(0,0);
		myOldCoordinates = new Coordinates(myCoordinates);
		myAngle = new Angle(0);
		myObservers = new ArrayList<>();
	}
	
	@Override
	public Angle getAngle() {
		return new Angle(myAngle);
	}

	@Override
	public Coordinates getCoordinates() {
		return new Coordinates(myCoordinates);
	}
	
	@Override
	public Coordinates getOldCoordinates() {
		return new Coordinates(myOldCoordinates);
	}

	@Override
	public void addDegree(Double d) {
		myAngle.addAngleValue(d);;
		notifyObservers();
	}

	@Override
	public void addCoordinates(Coordinates c) {
		myOldCoordinates = new Coordinates(myCoordinates);
		myCoordinates.addCoordinates(c);
		notifyObservers();
	}

	@Override
	public void setHidden(boolean b) {
		isHidden = b;
		notifyObservers();
	}

	@Override
	public void setActive(boolean b) {
		isActive = b;
		notifyObservers();
	}

	@Override
	public void setPenUp(boolean b) {
		isPenUp = b;
		notifyObservers();
	}

	@Override
	public boolean getHidden() {
		return isHidden;
	}

	@Override
	public boolean getActive() {
		return isActive;
	}

	@Override
	public boolean getPenUp() {
		return isPenUp;
	}

	private TurtleUpdate createTurtleUpdate(){
		return new TurtleUpdate(this);
	}
	
	@Override
	public void addObserver(TurtleObserver o) {
		myObservers.add(o);
	}

	@Override
	public void removeObserver(TurtleObserver o) {
		myObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(TurtleObserver o : myObservers){
			o.update(createTurtleUpdate());
		}
	}

	@Override
	public void setClear(boolean b) {
		isClear = b;
	}

	@Override
	public boolean getClear() {
		// TODO Auto-generated method stub
		boolean ret = isClear;
		isClear = false;
		return ret;
	}

	
	
}
