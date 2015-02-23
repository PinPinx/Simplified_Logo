package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import model.State;

public class Left extends SimpleTurtleCommand{
	
	public Left(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException{
		double param = referenceNode.execute(myState);
		myState.getTurtle().addDegree(param);
		return param;
	}

	
}