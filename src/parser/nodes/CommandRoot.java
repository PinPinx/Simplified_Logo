package parser.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import commands.Command;

public class CommandRoot extends ListNode {
	private String myString;
	
	public CommandRoot(String s, Stack<SyntaxNode> input){
		super(input);
		this.myString = s;
	}
	public String toString(){
		return new String(this.myString);
	}
	
}
