package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import model.Coordinates;
import model.Turtle;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public abstract class BinaryTurtleCommand extends BinaryNode {
	
	public BinaryTurtleCommand(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	protected double moveToPosition(double x, double y, Turtle turtle){
		double xDiff = turtle.getCoordinates().getX() - x;
		double yDiff = turtle.getCoordinates().getY() - y;
		double distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
		turtle.addCoordinates(new Coordinates(xDiff, yDiff));
		return distance;
	}
	
	
}