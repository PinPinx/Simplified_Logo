package model;

/**
 * A data object for 
 */
public class TurtleUpdate {
	private int turtleID, shapeID;
	private Angle turtleAngle;
	private Coordinates turtleOldCoordinates, turtleNewCoordinates;
	private boolean turtleHidden, turtleActive, 
		turtlePenUp, turtleClear, turtleStamp;
	
	
	public TurtleUpdate(TurtleSingle t){
		this.turtleID = t.getID();
		this.turtleAngle = t.getAngle();
		this.turtleOldCoordinates = t.getOldCoordinates();
		this.turtleNewCoordinates = t.getCoordinates();
		this.turtleHidden = t.getHidden();
		this.turtleActive = t.getInactive();
		this.turtlePenUp = t.getPenUp();
		this.shapeID = t.getShapeID();
		this.turtleStamp = t.getStamp();
	}
	
	public int getTurtleID(){
		return turtleID;
	}
	public Angle getTurtleAngle() {
		return turtleAngle;
	}
	public Coordinates getTurtleNewCoordinates() {
		return turtleNewCoordinates;
	}
	public Coordinates getTurtleOldCoordinates() {
		return turtleOldCoordinates;
	}
	public boolean isTurtleHidden() {
		return turtleHidden;
	}
	public boolean isTurtleInactive() {
		return turtleActive;
	}
	public boolean isTurtlePenUp() {
		return turtlePenUp;
	}
	
	public boolean isTurtleClear() {
		return turtleClear;
	}
	public int getShapeID(){
		return shapeID;
	}
	public boolean isStamp(){
		return turtleStamp;
	}
	
}
