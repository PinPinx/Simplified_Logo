package parser.queries;

import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;
import model.State;

public class ShowQuery extends SyntaxNode{
	public ShowQuery(){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return !myState.getTurtle().getHidden() ? 1 : 0;
	}
	
	
}
