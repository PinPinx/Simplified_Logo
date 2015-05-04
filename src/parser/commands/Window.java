package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Window extends SyntaxNode {
	public Window(Stack<SyntaxNode> input){
	}
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setFence(false);
		return 2;
	}
	
}
