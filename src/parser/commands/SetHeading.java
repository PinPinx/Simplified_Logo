package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;

public class SetHeading extends SimpleTurtleCommand{

	private double angleMoved;
	
	public SetHeading(Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(input);
	}
	
	public double execute(State myState) throws BadArgumentException{
		double param = referenceNode.execute(myState);
		angleMoved = (param - myState.getTurtle().getAngle().getAngleValue())%360;
		myState.getTurtle().getAngle().setAngleValue(param);
		return angleMoved;
	}
}
