package parser.queries;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class XCor extends SyntaxNode{
	public XCor(){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getCoordinates().getX();
	}
	
	
}
