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
	private ViewOptions myViewOptions;

	private List<Observer> myObservers;
	
	public TurtleSingle(int id){
		myCoordinates = new Coordinates(0,0);
		myOldCoordinates = new Coordinates(myCoordinates);
		myAngle = new Angle(0);
		myObservers = new ArrayList<>();
		ID = id;
		myViewOptions = new ViewOptions();
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
	public double addDegree(double degrees) {
		double param = degrees;
		myAngle.addAngleValue(param);
		myOldCoordinates = new Coordinates(myCoordinates);
		notifyObservers();
		return param;
	}

	@Override
	public double moveDistance(double distance) {
		double param = distance;
		myOldCoordinates = new Coordinates(myCoordinates);
		Coordinates change = distanceToCoordinates(param);
		myCoordinates.addCoordinates(change);
		notifyObservers();
		return param;
	}
	
	@Override
	public double setHeading(double degrees){
		double ret = Math.abs(myAngle.getAngleValue() - degrees);
		myAngle.setAngleValue(degrees);
		notifyObservers();
		return ret;
	}
//TODO: Clean this.
	@Override
	public double setTowards(double x, double y){
		double yDiff = myCoordinates.getY() - y;
		double xDiff = myCoordinates.getX() - x;
		if (xDiff == 0 && yDiff == 0)
			return 0;
		if (xDiff == 0)
			return setHeading(Math.toDegrees((yDiff > 0 ? 1 : -1)*Math.PI/2));
		double finalAngle = Math.atan(yDiff/xDiff);
		if (xDiff > 0)
			finalAngle += Math.PI;
		notifyObservers();
		return setHeading(Math.toDegrees(finalAngle));
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
		myViewOptions.addObserver(o);
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
	public double moveToPosition(double x, double y){
		Coordinates delta = new Coordinates(x - myCoordinates.getX(), y - myCoordinates.getY());
		//TODO: Following two lines may need to be put into a helper
		myOldCoordinates = new Coordinates(myCoordinates);
		myCoordinates.addCoordinates(delta);
		notifyObservers();
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
	
	public void changeViewOptions(ViewChanger vc){
		vc.change(myViewOptions);
	}
}
