package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class IsPenDown extends SyntaxNode{
	
	public IsPenDown(Stack<SyntaxNode> input){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return !myState.getTurtle().getPenUp() ? 1 : 0;
	}
	
	
}
