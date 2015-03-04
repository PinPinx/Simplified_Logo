package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Coordinates;
import model.State;
import model.Turtle;
import parser.nodes.SyntaxNode;

public class Home extends SyntaxNode {

	public Home(Stack<SyntaxNode> input) throws BadArgumentException{
	}
	
	//TODO: Duplicated with setXY, BinaryTurtleCommand
	@Override
	public double execute(State myState) throws BadArgumentException {
		Turtle turtle = myState.getTurtle();
		return turtle.moveToPosition(0, 0);
	}
}
