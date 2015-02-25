package parser.commands;

import java.util.Stack;

import parser.nodes.ConstantNode;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;
import model.Coordinates;
import model.State;

public class Forward extends SimpleTurtleCommand{
	
	public Forward(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	//TODO: Make interpret more transparent/right now it's hard to tell what the first line is doing
	@Override
	public double execute(State myState) throws BadArgumentException{
		double distance = (double) referenceNode.execute(myState);
		Coordinates displacement = angleToCoordinates(myState.getTurtle().getAngle(), distance);
		myState.getTurtle().addCoordinates(displacement);
		return distance;
	}

	public static void main(String[] args) throws BadArgumentException{
		Stack<SyntaxNode> stack = new Stack<SyntaxNode>();
		stack.push(new ConstantNode(50));
		Forward f = new Forward(stack);
		System.out.println(f.getClass().toString());
	}
	
}
