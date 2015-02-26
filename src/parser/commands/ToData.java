package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;

public class ToData extends SyntaxNode{

	private String myName;
	private ListNode varList;
	private ListNode commandList;
	
	//TODO: A little duplicated from For
	public ToData(String name, Stack<SyntaxNode> input){
		myName = name;
		commandList = (ListNode) input.pop();
		varList = (ListNode) input.pop();
	}
	
	public double execute(State myState){
		return myState.getCommandHistory().addCommand(this);
	}
	
	public String getName(){
		return new String(myName);
	}
	
	public ListNode getCommandList(){
		return commandList;
	}
	
	public ListNode getVarList(){
		return varList;
	}
}
