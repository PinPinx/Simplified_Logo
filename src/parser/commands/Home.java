package parser.commands;

import java.util.Stack;

import model.State;
import model.Turtle;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Home extends BinaryTurtleCommand {

	public Home(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO: Duplicated with setXY
	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		return moveToPosition(0, 0, turtle);
	}

}
