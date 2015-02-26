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
		Turtle turtle = myState.getTurtle();
		double startAngle = turtle.getAngle().getAngleValue();
		Point2D dir = new Point2D(nodeOne.execute(myState),nodeTwo.execute(myState));
		Point2D curr = new Point2D(turtle.getCoordinates().getX(),turtle.getCoordinates().getY());
		Point2D diff = dir.subtract(curr);
		double d = diff.dotProduct(1, 0)/diff.magnitude();
		int mult = diff.getY() <= 0 ? -1 : 1;
		double endAngleValue = mult*Math.acos(d)*360.0/2.0/Math.PI;
		Angle endAngle = new Angle(endAngleValue);
		endAngleValue = endAngle.getAngleValue();
		turtle.addDegree(endAngleValue - startAngle);
		return Math.abs(endAngleValue - startAngle);
	}

}
