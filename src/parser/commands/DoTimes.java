package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class DoTimes extends LoopingNode{

	public DoTimes(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (listReference.getSize() != 2)
			throw new BadArgumentException("The first bracketed list following a do times loop must only have two entries.");
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getVariablesCollection().enterScope();
		int limit;
		limit = (int) listReference.getNode(1).execute(myState);
		for (int i = 1; i<limit; i++){
			runCode(myState, i);
		}
		return runAndExitScope(myState, limit);
	}

}
