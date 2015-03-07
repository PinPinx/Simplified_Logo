package parser.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import model.IVariablesCollection;
import model.State;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;

public class ToInstance extends SyntaxNode{
	private List<SyntaxNode> paramList;
	private ListNode varList;
	private ListNode commandList;
	
	public ToInstance(ToData commandData, Stack<SyntaxNode> input){
		varList = commandData.getVarList();
		commandList = commandData.getCommandList();
		paramList = new ArrayList<SyntaxNode>();
		for (int i = 0; i < varList.getSize(); i++){
			paramList.add(input.pop());
		}
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		IVariablesCollection variables = myState.getVariablesCollection();
		variables.enterScope();
		for (int i=0; i < varList.getSize(); i++){
			String varName = ((VariableNode) varList.getNode(i)).getName();
			String newValue = Double.toString(paramList.get(i).execute(myState));
			setVariable(variables, varName, newValue);
		}
		double ret = commandList.execute(myState);
		variables.exitScope();
		return ret;
	}
	
	//Note: This is in loopingNode and here. This could be outsourced to a variablesetter
	//utility in order to prevent DupedCode. However, I was not sure if this was good design or not.
	private void setVariable(IVariablesCollection variables, String varName, String varValue) 
			throws BadArgumentException{
		try {
			variables.addVariable(varName, varValue);
		} catch (VariableCreationException
				| VariableCreationInvalidValueException e) {
			throw new BadArgumentException("Problem with variable factory.");
		}
	}
}
