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

public class For extends BinaryNode {

	private VariableNode variable;
	private ListNode listReference;
	
	public For(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(nodeOne instanceof ListNode) || !(nodeTwo instanceof ListNode)){
			throw new BadArgumentException("A for loop must be followed by two bracketed lists.");
		}
		listReference = (ListNode) nodeOne;
		if (listReference.getSize()!=4)
			throw new BadArgumentException("A for loop's first following bracketed list must have 4 entries.");
		if (!(listReference.getNode(0) instanceof VariableNode))
			throw new BadArgumentException("The first argument given in the for loop was not a variable.");
		variable = (VariableNode) listReference.getNode(0);
	}

	//TODO: Duplicate code with DoTimes
	@Override
	public double execute(State myState) throws BadArgumentException {
		int start = (int) listReference.getNode(1).execute(myState);
		int end = (int) listReference.getNode(2).execute(myState);
		int increment = (int) listReference.getNode(3).execute(myState);
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
			throw new BadArgumentException("");
		}
		return nodeTwo.execute(myState);
	}

}
