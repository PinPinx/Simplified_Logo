package model;

public interface Turtle extends ObservableTurtle {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public void addDegree(Double d);
	public void addCoordinates(Coordinates p);
	public double moveToPosition(double x, double y);
	public void setHidden(boolean b);
	public void setInactive(boolean b);
	public void setPenUp(boolean b);
	public void setClear(boolean b);
	public boolean getHidden();
	public boolean getInactive();
	public boolean getPenUp();
	public boolean getClear();
}

