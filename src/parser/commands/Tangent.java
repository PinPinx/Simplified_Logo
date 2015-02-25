package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Tangent extends UnaryNode{

	public Tangent(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.tan(referenceNode.execute(myState));
	}

}
