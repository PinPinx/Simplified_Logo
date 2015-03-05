package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnlimitedNode;

public class Sum extends UnlimitedNode{
	public Sum(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return nodeOne.execute(myState) + nodeTwo.execute(myState);
	}
}
