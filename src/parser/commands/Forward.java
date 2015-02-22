package parser.commands;

import java.util.Stack;

import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;
import model.Coordinates;
import model.State;

public class Forward extends SimpleTurtleCommand{
	
	public Forward(State s, Stack<SyntaxNode> input) throws BadArgumentException {
		super(s, input);
	}

	//TODO: Make interpret more transparent/right now it's hard to tell what the first line is doing
	@Override
	public double execute() throws BadArgumentException{
		double distance = (double) referenceNode.execute();
		Coordinates displacement = angleToCoordinates(myState.getTurtle().getAngle(), distance);
		myState.getTurtle().addCoordinates(displacement);
		return distance;
	}

	
	
}
