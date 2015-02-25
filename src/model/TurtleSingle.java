package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;

public class TurtleSingle implements Turtle {
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isActive;
	private List<Observer> myObservers;
	
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
	public void addObserver(Observer o) {
		myObservers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		myObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : myObservers){
			System.out.println("I notify el view: " + myObservers.size());
			o.update(createTurtleUpdate());
			System.out.println(myObservers.size());
		}
	}
	
}
