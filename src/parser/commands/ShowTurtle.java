package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class ShowTurtle extends SyntaxNode {
	
	public ShowTurtle(Stack<SyntaxNode> input){
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setHidden(false);
		return 1;
	}

}
