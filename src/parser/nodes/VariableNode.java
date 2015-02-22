package parser.nodes;

import Exceptions.BadArgumentException;
import Exceptions.VariableNotFoundException;
import model.State;

public class VariableNode extends SyntaxNode{
	private String myName;
	private State myState;
	
	public VariableNode(State s,String input) throws VariableNotFoundException{
		myName=input;
		myState=s;
		myState.getVariablesCollection().getVariableValue(myName);
		
	}
	
	@Override
	public double execute() throws BadArgumentException{
		try{
			return (Double) myState.getVariablesCollection().getVariableValue(myName);
		}
		catch(VariableNotFoundException e){
			throw new BadArgumentException();
		}
	}
}
