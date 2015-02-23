package parser.commands;

import java.util.Stack;

import parser.nodes.SyntaxNode;
import model.State;
import model.Turtle;
import Exceptions.BadArgumentException;

public class SetXY extends BinaryTurtleCommand {

	public SetXY(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO: May be duplicated code from SetHeading
	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		double newX = nodeOne.execute(myState);
		double newY = nodeTwo.execute(myState);
		return moveToPosition(newX, newY, turtle);
	}

}
