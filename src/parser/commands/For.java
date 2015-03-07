package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;

public class For extends LoopingNode {
	
	public For(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (listReference.getSize()!=4)
			throw new BadArgumentException("A for loop's first following bracketed list must have 4 entries.");
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getVariablesCollection().enterScope();
		int start = (int) listReference.getNode(1).execute(myState);
		int end = (int) listReference.getNode(2).execute(myState);
		int increment = (int) listReference.getNode(3).execute(myState);
		int count = start;
		while(count<end-increment+1){
			runCode(myState, count);
			count+=increment;
		}
		return runAndExitScope(myState, count);
	}

}
