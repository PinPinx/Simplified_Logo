package parser.math;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Tan extends UnaryNode{

	public Tan(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.tan(referenceNode.execute(myState));
	}

}
