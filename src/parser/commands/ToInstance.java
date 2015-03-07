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
			VariableNode currentVar = ((VariableNode) varList.getNode(i));
			String newValue = Double.toString(paramList.get(i).execute(myState));
			try {
				variables.addVariable(currentVar.getName(), newValue);
			} catch (VariableCreationException
					| VariableCreationInvalidValueException e) {
				throw new BadArgumentException("Problem with variable factory.");
			}
		}
		double ret = commandList.execute(myState);
		variables.exitScope();
		return ret;
	}
}
