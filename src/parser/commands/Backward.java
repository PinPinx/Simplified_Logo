package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import model.Coordinates;
import model.State;

public class Backward extends UnaryNode {

	public Backward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		return myState.getTurtle().moveDistance(-referenceNode.execute(myState));
	}
}
