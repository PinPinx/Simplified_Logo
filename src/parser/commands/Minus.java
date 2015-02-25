package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Minus extends UnaryNode{

	public Minus(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return -1 * referenceNode.execute(myState);
	}

}
