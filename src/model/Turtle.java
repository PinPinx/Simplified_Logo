package model;

public interface Turtle extends Observable {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public void addDegree(Double degrees);
	public void moveDistance(double distance);
	public double setHeading(double degrees);
	public double setTowards(double x, double y);
	public double moveToPosition(double x, double y);
	public void setHidden(boolean b);
	public void setInactive(boolean b);
	public void setPenUp(boolean b);
	public boolean getHidden();
	public boolean getInactive();
	public boolean getPenUp();
	public int getID();
}

