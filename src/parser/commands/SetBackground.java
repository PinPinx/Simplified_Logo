package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class SetBackground extends UnaryNode {
	
	public SetBackground(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		int ret = (int)referenceNode.execute(myState);
		myState.getTurtle().changePen(vo -> vo.setBackgroundID(ret));
		return ret;
	}

}
