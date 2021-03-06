package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.UnaryNode;
import parser.nodes.SyntaxNode;

public class Not extends UnaryNode{

	public Not(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return referenceNode.execute(myState) == 0 ? 1 : 0;
	}

}
