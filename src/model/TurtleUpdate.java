package model;

/**
 * A data object for 
 */
public class TurtleUpdate {
	private Angle turtleAngle;
	private Coordinates turtleOldCoordinates, turtleNewCoordinates;
	private boolean turtleHidden, turtleActive, turtlePenUp;
	
	public TurtleUpdate(Turtle t){
		this.turtleAngle = t.getAngle();
		this.turtleOldCoordinates = t.getOldCoordinates();
		this.turtleNewCoordinates = t.getCoordinates();
		this.turtleHidden = t.getHidden();
		this.turtleActive = t.getActive();
		this.turtlePenUp = t.getPenUp();
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
	public boolean isTurtleActive() {
		return turtleActive;
	}
	public boolean isTurtlePenUp() {
		return turtlePenUp;
	}
	
	
}
