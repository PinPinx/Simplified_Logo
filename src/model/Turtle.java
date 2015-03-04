package model;

public interface Turtle extends Observable {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public void addDegree(Double d);
	public void moveDistance(double d);
	public double moveToPosition(double x, double y);
	public void setHidden(boolean b);
	public void setInactive(boolean b);
	public void setPenUp(boolean b);
	public boolean getHidden();
	public boolean getInactive();
	public boolean getPenUp();
	public int getID();
}

