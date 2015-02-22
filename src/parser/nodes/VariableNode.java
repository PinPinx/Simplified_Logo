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
		myState.getVariablesCollection();
	}
	
	@Override
	public int interpret() throws BadArgumentException{
		try{
			return (Integer) myState.getVariablesCollection().getVariableValue(myName);
		}
		catch(VariableNotFoundException e){
			throw new BadArgumentException();
		}
	}
}
