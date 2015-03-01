package model;

import java.util.ArrayList;
import java.util.List;

import view.components.TurtleObserver;

public class TurtleSingle implements Turtle {
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isInactive, isClear;
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
		isInactive = b;
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
	public boolean getInactive() {
		return isInactive;
	}

	@Override
	public boolean getPenUp() {
		return isPenUp;
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
			o.update(new TurtleUpdate(this));
		}
	}

	@Override
	public void setClear(boolean b) {
		isClear = b;
		notifyObservers();
	}

	@Override
	public boolean getClear() {
		// TODO Auto-generated method stub
		boolean ret = isClear;
		isClear = false;
		return ret;
	}
	
	@Override
	public double moveToPosition(double x, double y){
		Coordinates delta = new Coordinates(x - myCoordinates.getX(), y - myCoordinates.getY());
		addCoordinates(delta);
		return delta.distance(getCoordinates());
	}
	
	
}
