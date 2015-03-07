package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class Right extends UnaryNode {

	public Right(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO: Duplicated code with left
	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().addDegree(-1*referenceNode.execute(myState));
	}

}
