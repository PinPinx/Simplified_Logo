package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.ConstantNode;
import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;

public class Forward extends SimpleTurtleCommand{
	
	public Forward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	//TODO: Make interpret more transparent/right now it's hard to tell what the first line is doing
	@Override
	public double execute(State myState) throws BadArgumentException{
		double distance = (double) referenceNode.execute(myState);
		Coordinates displacement = angleToCoordinates(myState.getTurtle().getAngle(), distance);
		myState.getTurtle().addCoordinates(displacement);
		return distance;
	}
	
}
