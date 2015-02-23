package parser.commands;

import model.State;
import Exceptions.BadArgumentException;
import parser.nodes.SyntaxNode;

public class HideTurtle extends SyntaxNode {

	boolean hide;
	
	public HideTurtle(State s, boolean input){
		hide = input;
	}
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getTurtle().setHidden(hide);
		return !hide ? 1 : 0;
	}

}
