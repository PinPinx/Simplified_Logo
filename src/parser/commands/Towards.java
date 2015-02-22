package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.SyntaxNode;
import Exceptions.BadArgumentException;

public class Towards extends SimpleTurtleCommand {

	public Towards(State s, Stack<SyntaxNode> input)
			throws BadArgumentException {
		super(s, input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double execute() throws BadArgumentException {
		// TODO Auto-generated method stub

	}

}
