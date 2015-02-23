package parser.bool;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class NotEqual extends BinaryNode{

	public NotEqual(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return nodeOne.execute(myState) != nodeTwo.execute(myState) ? 1 : 0;
	}

}
