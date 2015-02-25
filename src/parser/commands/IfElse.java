package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.TrinaryNode;

public class IfElse extends TrinaryNode {

	public IfElse(Stack<SyntaxNode> input) {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		if(nodeOne.execute(myState)!=0){
			return nodeTwo.execute(myState);
		}
		return nodeThree.execute(myState);
	}

}
