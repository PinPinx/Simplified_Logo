package parser.nodes;

import java.util.Stack;

import exceptions.BadArgumentException;

public abstract class TrinaryNode extends SyntaxNode{

	protected SyntaxNode nodeOne;
	protected SyntaxNode nodeTwo;
	protected SyntaxNode nodeThree;
	
	public TrinaryNode(Stack<SyntaxNode> input) throws BadArgumentException{
		if(input.size()<3){
			throw new BadArgumentException("Command requires 3 arguments.");
		}
		nodeOne = input.pop();
		nodeTwo = input.pop();
		nodeThree = input.pop();
	}
}
