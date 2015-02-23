package parser.queries;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class PenQuery extends SyntaxNode{
	
	public PenQuery(){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return !myState.getTurtle().getPenUp() ? 1 : 0;
	}
	
	
}
