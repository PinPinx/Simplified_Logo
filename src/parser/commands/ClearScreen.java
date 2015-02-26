package parser.commands;

import java.util.Stack;

import model.Coordinates;
import model.State;
import model.Turtle;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class ClearScreen extends SyntaxNode {
	public ClearScreen(Stack<SyntaxNode> input) {}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setClear(true);
		return moveToPosition(0, 0, myState.getTurtle());
	}
	
	//TODO REPEATED CODE WITH HOME
	private double moveToPosition(double x, double y, Turtle turtle){
		double xDiff = x - turtle.getCoordinates().getX();
		double yDiff = y - turtle.getCoordinates().getY();
		double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		turtle.addCoordinates(new Coordinates(xDiff, yDiff));
		return distance;
	}

}
