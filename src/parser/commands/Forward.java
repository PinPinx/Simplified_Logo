package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import model.State;

public class Forward extends UnaryNode{
	
	public Forward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException{
		double distance = referenceNode.execute(myState);
		myState.getTurtle().moveDistance(distance);
		return distance;
	}
	
}
