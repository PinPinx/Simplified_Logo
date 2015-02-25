package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class HideTurtle extends SyntaxNode {
	
	public HideTurtle(Stack<SyntaxNode> input){
	}
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setHidden(true);
		return 0;
	}

}
