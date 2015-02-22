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
		if (input.size() != 1)
			throw new BadArgumentException();
		referenceNode = input.pop();
	}
	
	public int interpret() throws BadArgumentException{
		return referenceNode.interpret();
	}
	
	protected Coordinates angleToCoordinates(Angle angle, double displacement){
		double param = angle.getAngleValueInRadians();
		double deltaX, deltaY;
		deltaX = Math.cos(param);
		deltaY = Math.sin(param);
		Coordinates returner = new Coordinates(deltaX, deltaY);
		returner.scale(displacement);
		return returner;
	}
}
