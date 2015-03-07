package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class SetHeading extends UnaryNode{	
	public SetHeading(Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		double param = referenceNode.execute(myState);
		return myState.getTurtle().setHeading(param);
	}
}
