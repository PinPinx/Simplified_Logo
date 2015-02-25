package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;

public class PenUp extends SyntaxNode {
	
	public PenUp(Stack<SyntaxNode> input){
	}
	
	@Override
	public double execute(State myState){
		myState.getTurtle().setPenUp(true);
		return 0;
	}

}
