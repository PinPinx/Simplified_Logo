package parser.math;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Sine extends UnaryNode{

	public Sine(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.sin(referenceNode.execute(myState));
	}

}
