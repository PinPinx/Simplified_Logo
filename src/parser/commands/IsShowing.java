package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import model.State;

public class IsShowing extends SyntaxNode{
	public IsShowing(Stack<SyntaxNode> input){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return !myState.getTurtle().getHidden() ? 1 : 0;
	}
	
	
}
