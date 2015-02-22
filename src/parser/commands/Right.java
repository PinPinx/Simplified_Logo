package parser.commands;

import java.util.Stack;

import Exceptions.BadArgumentException;
import model.Angle;
import model.State;
import parser.nodes.SyntaxNode;

public class Right extends SimpleTurtleCommand {

	public Right(State s, Stack<SyntaxNode> input) throws BadArgumentException{
		super(s, input);
	}
	@Override
	public double execute() throws BadArgumentException {
		double param = referenceNode.execute();
		myState.getTurtle().addAngle(new Angle(-1*param));
		return param;
	}

}
