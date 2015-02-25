package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class Heading extends SyntaxNode{
	
	public Heading(Stack<SyntaxNode> input){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getAngle().getAngleValue();
	}
	
	
}
