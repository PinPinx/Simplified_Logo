package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;

public class Backward extends SimpleTurtleCommand {

	public Backward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
	//TODO: This is duplicated code from Forward
	@Override
	public double execute(State myState) throws BadArgumentException{
		return myState.getTurtle().moveDistance(false, referenceNode, myState);
	}
}
