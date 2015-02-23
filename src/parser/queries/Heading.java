package parser.queries;

import Exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class Heading extends SyntaxNode{
	
	public Heading(){
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().getAngle().getAngleValue();
	}
	
	
}
