package parser.nodes;

import exceptions.BadArgumentException;
import model.State;


public class VariableNode extends SyntaxNode{
	private String myName;
	
	public VariableNode(String input){
		myName=input;
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		Object varValue = myState.getVariablesCollection().getVariableValue(myName);
		if (varValue instanceof Integer)
			return (double) ((Integer) varValue).intValue();
		return (double) myState.getVariablesCollection().getVariableValue(myName);
	}
	
	public String getName(){
		return new String(myName);
	}
}
