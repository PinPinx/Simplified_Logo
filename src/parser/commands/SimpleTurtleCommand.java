package parser.commands;

import java.util.Stack;

import model.Angle;
import model.Coordinates;
import model.State;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;

public abstract class SimpleTurtleCommand extends Executable {

	protected SyntaxNode referenceNode;
	
	public SimpleTurtleCommand(State s, Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(s);
		referenceNode = input.pop();
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
