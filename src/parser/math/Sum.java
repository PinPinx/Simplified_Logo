package parser.math;

import java.util.Stack;
import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class Sum extends BinaryNode{
	public Sum(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return nodeOne.execute(myState) + nodeTwo.execute(myState);
	}
}