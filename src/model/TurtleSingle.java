package model;

import java.util.ArrayList;
import java.util.List;

import view.components.TurtleObserver;

public class TurtleSingle implements Turtle {
	private int ID;
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isInactive, isClear;
	private List<TurtleObserver> myObservers;
	
	public TurtleSingle(int id){
		myCoordinates = new Coordinates(0,0);
		myOldCoordinates = new Coordinates(myCoordinates);
		myAngle = new Angle(0);
		myObservers = new ArrayList<>();
		ID = id;
	}
	
	public int getID(){
		return ID;
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
		myAngle.addAngleValue(d);
		myOldCoordinates = new Coordinates(myCoordinates);
		notifyObservers();
	}

	@Override
	public void moveDistance(double distance) {
		myOldCoordinates = new Coordinates(myCoordinates);
		Coordinates change = distanceToCoordinates(distance);
		myCoordinates.addCoordinates(change);
		notifyObservers();
	}

	@Override
	public void setHidden(boolean b) {
		isHidden = b;
		notifyObservers();
	}

	@Override
	public void setInactive(boolean b) {
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
		boolean ret = isClear;
		isClear = false;
		return ret;
	}
	//TODO: Check over this Kaighn?
	@Override
	public double moveToPosition(double x, double y){
		Coordinates delta = new Coordinates(x - myCoordinates.getX(), y - myCoordinates.getY());
		//TODO: Following two lines may need to be put into a helper
		myOldCoordinates = new Coordinates(myCoordinates);
		myCoordinates.addCoordinates(delta);
		return delta.distance(getCoordinates());
	}
	
	protected Coordinates distanceToCoordinates(double distance){
		double param = myAngle.getAngleValueInRadians();
		double deltaX, deltaY;
		deltaX = Math.cos(param);
		deltaY = Math.sin(param);
		Coordinates returner = new Coordinates(deltaX, deltaY);
		returner.scale(distance);
		return returner;
	}
}
