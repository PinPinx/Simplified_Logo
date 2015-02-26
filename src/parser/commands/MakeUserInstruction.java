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

public class MakeUserInstruction extends BinaryNode {

	private VariableNode variable;
	private ListNode commandName;
	
	public MakeUserInstruction(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(nodeOne instanceof ListNode) || !(nodeTwo instanceof ListNode))
			throw new BadArgumentException();
		commandName = (ListNode) nodeOne;
		if (commandName.getSize()!=4)
			throw new BadArgumentException();
		variable = (VariableNode) commandName.getNode(0);
	}

	//TODO: Duplicate code with DoTimes
	@Override
	public double execute(State myState) throws BadArgumentException {
		int start = (int) commandName.getNode(1).execute(myState);
		int end = (int) commandName.getNode(2).execute(myState);
		int increment = (int) commandName.getNode(3).execute(myState);
		int count = start;
		while(count<end-increment+1){
			runCode(myState, count);
			count+=increment;
		}
		return runCode(myState, count);
	}
	
	private double runCode(State myState, int i) throws BadArgumentException{
		//TODO: Refactor into method. This sets the variable to the desired value
		try {
			myState.getVariablesCollection().addVariable(variable.getName(), Integer.toString(i));
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			throw new BadArgumentException();
		}
		return nodeTwo.execute(myState);
	}

}
