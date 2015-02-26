package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import model.Coordinates;
import model.Turtle;
import parser.nodes.BinaryNode;
import parser.nodes.SyntaxNode;

public abstract class BinaryTurtleCommand extends BinaryNode {
	
	public BinaryTurtleCommand(Stack<SyntaxNode> input) throws BadArgumentException{
		super(input);
	}	
}