package parser.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.ConstantNode;
import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;

public class GetPenColor extends SyntaxNode{
	
	public GetPenColor(Stack<SyntaxNode> input) throws BadArgumentException {}

	@Override
	public double execute(State myState) throws BadArgumentException{
		return myState.getTurtle().getPenColor();
	}
	
}
