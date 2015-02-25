package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class Or extends BinaryNode{

	public Or(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return nodeOne.execute(myState) != 0 || nodeTwo.execute(myState) !=0 ? 1 : 0;
	}

}
