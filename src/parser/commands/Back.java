package parser.commands;

import java.util.Stack;

import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;
import Exceptions.BadArgumentException;

public class Back extends SimpleTurtleCommand {

	public Back(State s, Stack<SyntaxNode> input) throws BadArgumentException {
		super(s, input);
	}
	//TODO: This is duplicated code
	@Override
	public void execute() throws BadArgumentException{
		double distance = (double) referenceNode.interpret();
		Coordinates displacement = angleToCoordinates(myState.getTurtle().getAngle(), distance);
		myState.getTurtle().addCoordinates(displacement);
	}
}
