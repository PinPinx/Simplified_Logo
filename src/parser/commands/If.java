package parser.commands;

import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public class If extends BinaryNode {

	public If(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		if(nodeOne.execute(myState)!=1){
			return nodeTwo.execute(myState);
		}
		return 0;
	}

}
