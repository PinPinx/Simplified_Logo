package parser.nodes;

import Exceptions.BadArgumentException;

public abstract class SyntaxNode {

	/**
	 * 
	 * @return int that allows another SyntaxNode to interpret node as an int
	 * @throws BadArgumentException
	 */
	public abstract int interpret() throws BadArgumentException;
	
}
