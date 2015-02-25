package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class DoTimes extends BinaryNode {

	public DoTimes(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		for (int i = 0; i<(int) nodeOne.execute(myState)-1; i++){
			nodeTwo.execute(myState);
		}
		return nodeTwo.execute(myState);
	}

}
