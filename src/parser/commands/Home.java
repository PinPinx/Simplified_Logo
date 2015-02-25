package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Coordinates;
import model.State;
import model.Turtle;
import parser.nodes.SyntaxNode;

public class Home extends SyntaxNode {

	public Home(Stack<SyntaxNode> input) throws BadArgumentException{
	}
	
	//TODO: Duplicated with setXY, BinaryTurtleCommand
	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		return moveToPosition(0, 0, turtle);
	}
	
	protected double moveToPosition(double x, double y, Turtle turtle){
		double xDiff = turtle.getCoordinates().getX() - x;
		double yDiff = turtle.getCoordinates().getY() - y;
		double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		turtle.addCoordinates(new Coordinates(xDiff, yDiff));
		return distance;
	}

}
