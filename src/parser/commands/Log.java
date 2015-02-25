package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Log extends UnaryNode{

	public Log(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		return Math.log(referenceNode.execute(myState));
	}

}
