package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;

public class SetHeading extends SimpleTurtleCommand{

	private double angleMoved;
	
	public SetHeading(State s, Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(s, input);
		// TODO Auto-generated constructor stub
	}
	
	public double execute() throws BadArgumentException{
		double param = referenceNode.execute();
		angleMoved = (param - myState.getTurtle().getAngle().getAngleValue())%360;
		myState.getTurtle().getAngle().setAngleValue(param);
		return angleMoved;
	}
}
