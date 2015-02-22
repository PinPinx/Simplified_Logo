package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import model.Angle;
import model.State;

public class Left extends SimpleTurtleCommand{
	
	public Left(State s, Stack<SyntaxNode> input) throws BadArgumentException {
		super(s, input);
	}

	@Override
	public void execute() throws BadArgumentException{
		myState.getTurtle().addAngle(new Angle(interpret()));
	}

	
}
