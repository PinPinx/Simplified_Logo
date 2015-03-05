package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Pi extends SyntaxNode {
	public Pi(Stack<SyntaxNode> input) {
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		return Math.PI;
	}
	

}
