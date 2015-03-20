package parser.commands;

import java.util.Stack;

import model.State;
import exceptions.BadArgumentException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import parser.nodes.BinaryNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;

public abstract class LoopingNode extends BinaryNode {

	protected String variableName;
	protected ListNode listReference;
	
	public LoopingNode(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(nodeOne instanceof ListNode) || !(nodeTwo instanceof ListNode))
			throw new BadArgumentException("A loop must be followed by two bracketed lists.");
		listReference = (ListNode) nodeOne;
		if (!(listReference.getNode(0) instanceof VariableNode))
			throw new BadArgumentException("The first argument given in the for loop was not a variable.");
		variableName = ((VariableNode) listReference.getNode(0)).getName();
	}

	protected double runCode(State myState, int i) throws BadArgumentException{
		try {
			myState.getVariablesCollection().addVariable(variableName, Integer.toString(i));
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			throw new BadArgumentException("This should not happen. There is a problem with the variable factory.");
		}
		return nodeTwo.execute(myState);
	}

	protected double runAndExitScope(State myState, int i) throws BadArgumentException{
		double ret = runCode(myState, i);
		myState.getVariablesCollection().exitScope();
		return ret;
	}
}
