package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import model.State;
import parser.nodes.BinaryNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;

public class DoTimes extends BinaryNode {

	private VariableNode variable;
	
	public DoTimes(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(nodeOne instanceof ListNode) || !(nodeTwo instanceof ListNode))
			throw new BadArgumentException();
		if (((ListNode) nodeOne).getSize()!=2)
			throw new BadArgumentException();
		variable = (VariableNode) ((ListNode) nodeOne).getNode(0);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		int limit;
		limit = (int) ((ListNode) nodeOne).getNode(1).execute(myState);
		try {
			myState.getVariablesCollection().addVariable(variable.getName(), "1");
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			throw new BadArgumentException();
		}
		for (int i = 1; i<limit; i++){
			runCode(myState, i);
		}
		return runCode(myState, limit);
	}
	
	private double runCode(State myState, int i) throws BadArgumentException{
		//TODO: Refactor into method. This sets the varaible to the desired value
		try {
			myState.getVariablesCollection().addVariable(variable.getName(), Integer.toString(i));
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			throw new BadArgumentException();
		}
		return nodeTwo.execute(myState);
	}

}