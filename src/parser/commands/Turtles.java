package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Turtles extends SyntaxNode {
	
	public Turtles(Stack<SyntaxNode> input) {
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().numTurtles();
	}

}
