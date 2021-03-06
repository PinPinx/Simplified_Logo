package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class SetShape extends UnaryNode {
	
	public SetShape(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		int ret = (int)referenceNode.execute(myState);
		myState.getTurtle().setShapeID(ret);
		return ret;
	}

}