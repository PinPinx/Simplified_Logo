package parser.math;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.BinaryNode;

public class Power extends BinaryNode{

	public Power(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.pow(nodeOne.execute(myState), nodeTwo.execute(myState));
	}

}
