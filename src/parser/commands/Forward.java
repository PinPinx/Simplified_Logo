package parser.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import exceptions.BadArgumentException;
import parser.nodes.ConstantNode;
import parser.nodes.SyntaxNode;
import model.Coordinates;
import model.State;

public class Forward extends SimpleTurtleCommand{
	
	public Forward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException{
		double distance = referenceNode.execute(myState);
		myState.getTurtle().moveDistance(distance);
		return distance;
	}
	
}
