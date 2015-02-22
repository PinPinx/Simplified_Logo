package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import model.Angle;
import model.State;
import parser.nodes.SyntaxNode;

public class Right extends SimpleTurtleCommand {

	public Right(State s, Stack<SyntaxNode> input) throws BadArgumentException{
		super(s, input);
	}
	@Override
	public void execute() throws BadArgumentException {
		myState.getTurtle().addAngle(new Angle(-1*interpret()));
	}

}
