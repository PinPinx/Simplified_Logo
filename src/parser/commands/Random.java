package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class Random extends SyntaxNode {
	public Random(Stack<SyntaxNode> input) {}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		return Math.random();
	}
	

}
