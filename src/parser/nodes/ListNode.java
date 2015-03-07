package parser.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exceptions.BadArgumentException;
import model.State;

public class ListNode extends SyntaxNode {

	protected List<SyntaxNode> myList= new ArrayList<SyntaxNode>();
	
	public ListNode(Stack<SyntaxNode> input){
		if (input.empty()){
			myList.add(new ConstantNode(0));
		}
		while (!input.empty()){
			myList.add(input.pop());
		}
	}

	public SyntaxNode getNode(int index){
			return myList.get(index);
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		myState.getVariablesCollection().enterScope();
		double ret = executeNodes(myState);
		myState.getVariablesCollection().exitScope();
		return ret;
	}
	
	protected double executeNodes(State myState) throws BadArgumentException{
		for (int i=0; i<myList.size()-1; i++){
			myList.get(i).execute(myState);
		}
		double ret = myList.get(myList.size()-1).execute(myState);
		return ret;
	}
	
	public int getSize(){
		return myList.size();
	}
}
