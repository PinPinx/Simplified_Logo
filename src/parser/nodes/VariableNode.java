package parser.nodes;

import exceptions.BadArgumentException;
import exceptions.VariableNotFoundException;
import model.State;


public class VariableNode extends SyntaxNode{
	private String myName;
	
	public VariableNode(String input){
		myName=input;
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
			return (double) myState.getVariablesCollection().getVariableValue(myName);
	}
	
	public String getName(){
		return myName;
	}
}
