package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Fence extends SyntaxNode {
	public Fence(Stack<SyntaxNode> input){
	}
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setFence(true);
		return 3;
	}
	
}
