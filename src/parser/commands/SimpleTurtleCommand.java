package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Angle;
import model.Coordinates;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public abstract class SimpleTurtleCommand extends UnaryNode {
	
	public SimpleTurtleCommand(Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(input);
	}
	
	protected Coordinates angleToCoordinates(Angle angle, double distance){
		double param = angle.getAngleValueInRadians();
		double deltaX, deltaY;
		deltaX = Math.cos(param);
		deltaY = Math.sin(param);
		Coordinates returner = new Coordinates(deltaX, deltaY);
		returner.scale(distance);
		return returner;
	}
}
