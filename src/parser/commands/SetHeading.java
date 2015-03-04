package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class SetHeading extends SimpleTurtleCommand{	
	public SetHeading(Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		double param = referenceNode.execute(myState);
		return myState.getTurtle().setHeading(param);
	}
}
