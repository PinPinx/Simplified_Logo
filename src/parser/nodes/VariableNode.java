package parser.nodes;

import model.State;
import Exceptions.BadArgumentException;
import Exceptions.VariableNotFoundException;


public class VariableNode extends SyntaxNode{
	private String myName;
	
	public VariableNode(String input){
		myName=input;
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		try{
			return (double) myState.getVariablesCollection().getVariableValue(myName);
		}
		catch(VariableNotFoundException e){
			throw new BadArgumentException();
		}
	}
}