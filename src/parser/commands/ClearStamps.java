package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;
import exceptions.BadArgumentException;

public class ClearStamps extends SyntaxNode {
	public ClearStamps(Stack<SyntaxNode> input) {}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getViewOptions().setClearStamps(true);
		return myState.getViewOptions().isStampsOut() ? 1 : 0;
	}
}
