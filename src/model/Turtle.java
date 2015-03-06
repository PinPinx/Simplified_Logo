package model;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public interface Turtle extends Observable {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public double addDegree(double degrees);
	public double moveDistance(double distance);
	public double setHeading(double degrees);
	public double setTowards(double x, double y);
	public double moveToPosition(double x, double y);
	public void setHidden(boolean b);
	public void setInactive(boolean b);
	public void setPenUp(boolean b);
	public void setStamp(boolean b);
	public boolean getHidden();
	public boolean getInactive();
	public boolean getPenUp();
	public boolean getStamp();
	public int getID();
	public void changePen(PenChanger vc);
	public void setShapeID(int ID);
	public int getShapeID();
}

