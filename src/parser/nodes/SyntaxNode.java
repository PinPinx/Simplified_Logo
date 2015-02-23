package parser.nodes;

import model.State;
import Exceptions.BadArgumentException;

public abstract class SyntaxNode {

	/**
	 * 
	 * @param myState TODO
	 * @return double that allows another SyntaxNode to interpret node as an int
	 * @throws BadArgumentException
	 */
	public abstract double execute(State myState) throws BadArgumentException;
	
}
