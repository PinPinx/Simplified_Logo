package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import model.State;
import model.Turtle;

public class SetPosition extends BinaryTurtleCommand {

	public SetPosition(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO: May be duplicated code from SetHeading
	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		double newX = nodeOne.execute(myState);
		double newY = nodeTwo.execute(myState);
		return turtle.moveToPosition(newX, newY);
	}

}
