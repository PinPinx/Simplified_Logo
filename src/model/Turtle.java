package model;

public interface Turtle extends Observable {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public void addDegree(Double d);
	public void addCoordinates(Coordinates p);
	public void setHidden(boolean b);
	public void setActive(boolean b);
	public void setPenUp(boolean b);
	public boolean getHidden();
	public boolean getActive();
	public boolean getPenUp();
}
