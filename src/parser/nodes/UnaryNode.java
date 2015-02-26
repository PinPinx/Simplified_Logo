package parser.nodes;

import java.util.Stack;

import view.View;
import exceptions.BadArgumentException;

public abstract class UnaryNode extends SyntaxNode {

	protected SyntaxNode referenceNode;
	
	public UnaryNode(Stack<SyntaxNode> input) throws BadArgumentException{
		if (input.empty()){
			throw new BadArgumentException("Command takes one argument, not none.");
		}
		referenceNode = input.pop();
	}
}
