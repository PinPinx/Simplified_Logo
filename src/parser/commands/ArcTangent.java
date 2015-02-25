package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class ArcTangent extends UnaryNode{

	public ArcTangent(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.atan(referenceNode.execute(myState));
	}

}
