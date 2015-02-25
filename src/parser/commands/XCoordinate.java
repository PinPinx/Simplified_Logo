package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class XCoordinate extends SyntaxNode{
	public XCoordinate(Stack<SyntaxNode> input){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getCoordinates().getX();
	}
	
	
}
