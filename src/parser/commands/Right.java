package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Angle;
import model.State;
import parser.nodes.SyntaxNode;

public class Right extends SimpleTurtleCommand {

	public Right(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}
	
	//TODO: Duplicated code with left
	@Override
	public double execute(State myState) throws BadArgumentException {
		double param = referenceNode.execute(myState);
		myState.getTurtle().addDegree(-1*param);
		return param;
	}

}
