package parser.commands;

import java.util.Stack;

import javafx.geometry.Point2D;
import exceptions.BadArgumentException;
import model.Angle;
import model.State;
import model.Turtle;
import parser.nodes.SyntaxNode;

public class SetTowards extends BinaryTurtleCommand {

	public SetTowards(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		return myState.getTurtle().setTowards(nodeOne.execute(myState), nodeTwo.execute(myState));
	}

}
