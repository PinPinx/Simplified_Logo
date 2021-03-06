package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class GreaterThan extends BinaryNode{

	public GreaterThan(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return nodeOne.execute(myState) > nodeTwo.execute(myState) ? 1 : 0;
	}

}
