package parser.nodes;

import java.util.List;
import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;

public class CommandRoot extends ListNode {
	private String myString;
	
	public CommandRoot(String s, Stack<SyntaxNode> input){
		super(input);
		this.myString = s;
	}
	public String toString(){
		return new String(this.myString);
	}
	
	public List<SyntaxNode> getChildren(){
		return myList;
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		return executeNodes(myState);
	}
}
