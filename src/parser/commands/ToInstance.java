package parser.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.VariableCreationException;
import exceptions.VariableCreationInvalidValueException;
import model.State;
import model.VariablesCollection;
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
		VariablesCollection variables = myState.getVariablesCollection();
		for (int i=0; i < varList.getSize(); i++){
			VariableNode currentVar = ((VariableNode) varList.getNode(i));
			String newValue = Double.toString(paramList.get(i).execute(myState));
			try {
				variables.addVariable(currentVar.getName(), newValue);
			} catch (VariableCreationException
					| VariableCreationInvalidValueException e) {
				throw new BadArgumentException();
			}
		}
		return commandList.execute(myState);
	}
}
