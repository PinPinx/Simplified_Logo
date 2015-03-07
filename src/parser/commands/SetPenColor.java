package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;
import model.ViewOptions;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public class SetPenColor extends UnaryNode {
	
	public SetPenColor(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO duplicated with a lot of view commands
	@Override
	public double execute(State myState) throws BadArgumentException{
		int ret = (int)referenceNode.execute(myState);
		myState.getTurtle().changePen(vo -> vo.setPenColorID(ret));
		return ret;
	}

}