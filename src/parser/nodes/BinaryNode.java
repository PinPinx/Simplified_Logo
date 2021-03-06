package parser.nodes;

import java.util.Stack;

import exceptions.BadArgumentException;

public abstract class BinaryNode extends SyntaxNode{

	protected SyntaxNode nodeOne;
	protected SyntaxNode nodeTwo;

	public BinaryNode(Stack<SyntaxNode> input) throws BadArgumentException{
		if(input.size()<2){
			throw new BadArgumentException("Command requires two arguments.");
		}
		nodeOne = input.pop();
		nodeTwo = input.pop();
	}

}