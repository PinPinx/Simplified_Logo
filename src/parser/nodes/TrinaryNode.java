package parser.nodes;

import java.util.Stack;

public abstract class TrinaryNode extends SyntaxNode{

	protected SyntaxNode nodeOne;
	protected SyntaxNode nodeTwo;
	protected SyntaxNode nodeThree;
	
	public TrinaryNode(Stack<SyntaxNode> input){
		nodeOne = input.pop();
		nodeTwo = input.pop();
		nodeThree = input.pop();
	}
}
