package parser.commands;

import parser.nodes.SyntaxNode;
import model.State;
import Exceptions.BadArgumentException;

public abstract class Executable extends SyntaxNode implements Command{

	protected State myState;
	
	public Executable(State s) throws BadArgumentException{
		myState = s;
		//validityCheck(input);
	}
	
	/*private void validityCheck(Stack<SyntaxNode> input) throws BadArgumentException{
		for (SyntaxNode s: input){
			try{
				s.interpret();
			}
			catch (BadArgumentException e) {
				throw e;
			}
		}
	}*/

}
