package parser.control;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class Make extends BinaryNode{

	public Make(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	public double execute(State myState){
		myState.getVariablesCollection().addVariable(nodeOne, nodeTwo.execute(myState));
	}
}
