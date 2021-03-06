package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import model.State;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;

public class MakeVariable extends BinaryNode{

	public MakeVariable(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if(!(nodeOne instanceof VariableNode))
			throw new BadArgumentException("A variable must follow the make/set declaration.");
	}

	public double execute(State myState) throws BadArgumentException{
		double expression = nodeTwo.execute(myState);
		try {
			myState.getVariablesCollection().addVariable(((VariableNode) nodeOne).getName(), Double.toString(expression));
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expression;
	}
}
