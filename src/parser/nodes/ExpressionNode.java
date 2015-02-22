package parser.nodes;

import java.util.Stack;

public abstract class ExpressionNode extends SyntaxNode{
	
		protected Stack<SyntaxNode> myInput;
		
		public ExpressionNode(Stack<SyntaxNode> input){
			myInput = input;
		}

}
