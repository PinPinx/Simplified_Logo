package parser.commands;

import java.util.Stack;

import model.Coordinates;
import model.State;
import model.Turtle;
import exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class ClearScreen extends SyntaxNode {
	public ClearScreen(Stack<SyntaxNode> input) {}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setClear(true);
		return myState.getTurtle().moveToPosition(0, 0);
	}
}
