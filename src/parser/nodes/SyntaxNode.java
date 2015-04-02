package parser.nodes;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;

public abstract class SyntaxNode {

	/**
	 * 
	 * @param myState TODO
	 * @return double that allows another SyntaxNode to interpret node as an int
	 * @throws BadArgumentException
	 */
	public abstract double execute(State myState) throws BadArgumentException;
	
}
