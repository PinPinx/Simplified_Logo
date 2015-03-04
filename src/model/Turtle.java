package model;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public interface Turtle extends Observable {
	public Angle getAngle(); 
	public Coordinates getCoordinates();
	public Coordinates getOldCoordinates();
	public double addDegree(boolean direction, SyntaxNode node, State myState) throws BadArgumentException;
	public double moveDistance(boolean direction, SyntaxNode node, State myState) throws BadArgumentException;
	public double moveToPosition(SyntaxNode x, SyntaxNode y, State myState);
	public void setHidden(boolean b);
	public void setInactive(boolean b);
	public void setPenUp(boolean b);
	public boolean getHidden();
	public boolean getInactive();
	public boolean getPenUp();
	public int getID();
}

