package parser.nodes;

import java.util.Stack;

import Exceptions.BadArgumentException;

public abstract class UnaryNode extends SyntaxNode {

	protected SyntaxNode referenceNode;
	
	public UnaryNode(Stack<SyntaxNode> input) throws BadArgumentException{
		if (input.empty()){
			throw new BadArgumentException();
		}
		referenceNode = input.pop();
	}
}
