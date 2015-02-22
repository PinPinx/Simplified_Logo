package parser.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Exceptions.BadArgumentException;

public class ListNode extends SyntaxNode {

	protected List<SyntaxNode> myList= new ArrayList<SyntaxNode>();
	
	public ListNode(Stack<SyntaxNode> input){
		while (!input.empty()){
			myList.add(input.pop());
		}
	}

	public List<SyntaxNode> getList(){
			return myList;
	}
	
	@Override
	public double execute() throws BadArgumentException {
		for (int i=0; i<myList.size()-1; i++){
			myList.get(i).execute();
		}
		return myList.get(myList.size()-1).execute();
	}
}
