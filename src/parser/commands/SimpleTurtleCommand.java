package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Angle;
import model.Coordinates;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;

public abstract class SimpleTurtleCommand extends UnaryNode {
	//TODO: Remove this from the hierarchy.
	public SimpleTurtleCommand(Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(input);
	}
	
}
