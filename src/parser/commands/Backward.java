package parser.commands;

import java.util.Stack;

import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;
import Exceptions.BadArgumentException;

public class Backward extends SimpleTurtleCommand {

	public Backward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	//TODO: This is duplicated code from Forward
	@Override
	public double execute(State myState) throws BadArgumentException{
		double distance = (double) referenceNode.execute(myState);
		Coordinates displacement = angleToCoordinates(myState.getTurtle().getAngle(), distance);
		myState.getTurtle().addCoordinates(displacement);
		return distance;
	}
}
