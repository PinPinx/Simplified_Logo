package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class ID extends SyntaxNode{
	public ID(Stack<SyntaxNode> input) {
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getID();
	}
}
