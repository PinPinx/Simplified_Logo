package model;

import java.util.ArrayList;
import java.util.List;

import view.Observer;
import view.TurtleImage;

public class TurtleSingle implements Turtle {
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isActive;
	private List<Observer> myObservers;
	
	public TurtleSingle(){
		this.myCoordinates = new Coordinates(0,0);
		this.myAngle = new Angle(0);
		this.myObservers = new ArrayList<>();
	}
	
	@Override
	public Angle getAngle() {
		return new Angle(this.myAngle);
	}

	@Override
	public Coordinates getCoordinates() {
		return new Coordinates(this.myCoordinates);
	}
	
	@Override
	public Coordinates getOldCoordinates() {
		return new Coordinates(this.myOldCoordinates);
	}

	@Override
	public void addAngle(Angle a) {
		this.myAngle.addAngle(a);
		notifyObservers();
	}

	@Override
	public void setAngle(Angle a) {
		this.myAngle = new Angle(a);
		notifyObservers();
	}

	@Override
	public void addCoordinates(Coordinates c) {
		this.myOldCoordinates = new Coordinates(this.myCoordinates);
		this.myCoordinates.addCoordinates(c);
		notifyObservers();
	}

	@Override
	public void setCoordinates(Coordinates c) {
		this.myOldCoordinates = new Coordinates(this.myCoordinates);
		this.myCoordinates = c;
		notifyObservers();
	}

	@Override
	public void setHidden(boolean b) {
		this.isHidden = b;
		notifyObservers();
	}

	@Override
	public void setActive(boolean b) {
		this.isActive = b;
		notifyObservers();
	}

	@Override
	public void setPenUp(boolean b) {
		this.isPenUp = b;
		notifyObservers();
	}

	@Override
	public boolean getHidden() {
		return this.isHidden;
	}

	@Override
	public boolean getActive() {
		return this.isActive;
	}

	@Override
	public boolean getPenUp() {
		return this.isPenUp;
	}

	private TurtleUpdate createTurtleUpdate(){
		return new TurtleUpdate(this);
	}
	
	@Override
	public void addObserver(Observer o) {
		this.myObservers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.myObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : this.myObservers){
			o.update(createTurtleUpdate());
			System.out.println(this.myObservers.size());
		}
	}
	
}
