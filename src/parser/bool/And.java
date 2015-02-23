package parser.bool;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class And extends BinaryNode{

	public And(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return nodeOne.execute(myState) != 0 && nodeTwo.execute(myState) !=0 ? 1 : 0;
	}

}
