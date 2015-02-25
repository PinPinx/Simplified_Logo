package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;

public class PenDown extends SyntaxNode {
	
	public PenDown(Stack<SyntaxNode> input){
	}
	
	@Override
	public double execute(State myState){
		myState.getTurtle().setPenUp(false);
		return 1;
	}

}