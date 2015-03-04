package model;

import java.util.ArrayList;
import java.util.List;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import view.components.Observer;

public class TurtleSingle implements Turtle {
	private int ID;
	private Coordinates myCoordinates, myOldCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isInactive;
	private List<Observer> myObservers;
	
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
	public double addDegree(boolean direction, SyntaxNode turnNode, State myState) throws BadArgumentException {
		double param = turnNode.execute(myState);
		myAngle.addAngleValue((direction ? 1:-1)*param);
		myOldCoordinates = new Coordinates(myCoordinates);
		notifyObservers();
		return param;
	}

	@Override
	public double moveDistance(boolean direction, SyntaxNode distance, State myState) throws BadArgumentException {
		double param = distance.execute(myState);
		myOldCoordinates = new Coordinates(myCoordinates);
		Coordinates change = distanceToCoordinates((direction ? 1:-1)*param);
		myCoordinates.addCoordinates(change);
		notifyObservers();
		return param;
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
			o.update(new TurtleUpdate(this));
		}
	}


	//TODO: Check over this Kaighn?
	@Override
	public double moveToPosition(SyntaxNode x, SyntaxNode y, State myState){
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
