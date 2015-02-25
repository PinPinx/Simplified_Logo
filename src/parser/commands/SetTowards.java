package parser.commands;

import java.util.Stack;

import model.Angle;
import model.State;
import model.Turtle;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;

public class SetTowards extends BinaryTurtleCommand {

	public SetTowards(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}

	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		Angle startAngle = turtle.getAngle();
		double xDiff = turtle.getCoordinates().getX() - nodeOne.execute(myState);
		double yDiff = turtle.getCoordinates().getY() - nodeTwo.execute(myState);
		Angle endAngle = new Angle(Math.atan(yDiff/xDiff));
		double angleDiff = endAngle.getAngleValue() - startAngle.getAngleValue();
		turtle.addDegree(angleDiff);
		return angleDiff;
	}

}
