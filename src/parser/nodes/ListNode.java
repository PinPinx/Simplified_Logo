package parser.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.State;
import Exceptions.BadArgumentException;

public class ListNode extends SyntaxNode {

	protected List<SyntaxNode> myList= new ArrayList<SyntaxNode>();
	
	public ListNode(Stack<SyntaxNode> input){
		while (!input.empty()){
			myList.add(input.pop());
		}
	}

	public SyntaxNode getNode(int index){
			return myList.get(index);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		for (int i=0; i<myList.size()-1; i++){
			myList.get(i).execute(myState);
		}
		return myList.get(myList.size()-1).execute(myState);
	}
	
	public int getSize(){
		return myList.size();
	}
}
