package parser.commands;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class YCor extends SyntaxNode{
	public YCor(){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getCoordinates().getY();
	}
	
	
}
