package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class SetPenSize extends UnaryNode {
	
	public SetPenSize(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		double ret = referenceNode.execute(myState);
		myState.getTurtle().changeViewOptions(vo -> vo.setPenSize(ret));
		return ret;
	}

}