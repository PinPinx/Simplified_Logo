package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;
import exceptions.BadArgumentException;

public class Stamp extends SyntaxNode{
	public Stamp(Stack<SyntaxNode> input) {}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setStamp(true);
		myState.getViewOptions().setStampsOut(true);
		return myState.getTurtle().getShapeID();
	}
}
