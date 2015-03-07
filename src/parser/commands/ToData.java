package parser.commands;

import java.util.Stack;

import model.State;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;

public class ToData extends SyntaxNode{

	private String myName;
	private String myDeclaration;
	private ListNode varList;
	private ListNode commandList;
	
	//TODO: A little duplicated from For
	public ToData(String name, String declaration, Stack<SyntaxNode> input){
		myName = name;
		myDeclaration = declaration;
		varList = (ListNode) input.pop();
		commandList = (ListNode) input.pop();
	}
	
	public double execute(State myState){
		return myState.getCommandHistory().addUDCommand(this) ? 1:0;
	}
	
	public String getName(){
		return new String(myName);
	}
	
	public String getDeclaration(){
		return new String(myDeclaration);
	}
	
	public ListNode getCommandList(){
		return commandList;
	}
	
	public ListNode getVarList(){
		return varList;
	}
}
