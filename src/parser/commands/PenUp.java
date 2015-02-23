package parser.commands;

import model.State;
import parser.nodes.SyntaxNode;

public class PenUp extends SyntaxNode {

	boolean penUp;
	
	public PenUp(boolean input){
		penUp=input;
	}
	
	@Override
	public double execute(State myState){
		myState.getTurtle().setPenUp(penUp);
		return !penUp ? 1 : 0;
	}

}
