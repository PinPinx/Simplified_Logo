package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class YCor extends SyntaxNode{
	public YCor(Stack<SyntaxNode> input){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getCoordinates().getY();
	}
	
	
}
