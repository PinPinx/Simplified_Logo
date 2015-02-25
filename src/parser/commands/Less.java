package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class Less extends BinaryNode{

	public Less(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return nodeOne.execute(myState) < nodeTwo.execute(myState) ? 1 : 0;
	}

}
