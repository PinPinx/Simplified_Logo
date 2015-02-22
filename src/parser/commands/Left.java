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
	public double execute() throws BadArgumentException{
		double param = referenceNode.execute();
		myState.getTurtle().addAngle(new Angle(param));
		return param;
	}

	
}
