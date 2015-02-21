package model;

public interface Turtle {
	public Angle getAngle(); 
	public Coordinates getPoint();
	public void addAngle(Angle a);
	public void setAngle(Angle a);
	public void addCoordinates(Coordinates p);
	public void setCoordinates(Coordinates p);
	public void setHidden(boolean b);
	public void setActive(boolean b);
	public void setPenUp(boolean b);
	public boolean getHidden();
	public boolean getActive();
	public boolean penUp();
}
