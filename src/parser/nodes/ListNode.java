package parser.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Exceptions.BadArgumentException;

public class ListNode extends SyntaxNode {

	protected List<SyntaxNode> myList= new ArrayList<SyntaxNode>();
	
	public ListNode(Stack<SyntaxNode> input){
		myList.addAll(input);
	}

	public List<SyntaxNode> getList(){
			return myList;
	}
	
	@Override
	public int interpret() throws BadArgumentException {
		try{
			return myList.get(myList.size()-1).interpret();
		}
		catch(BadArgumentException e){
			throw e;
		}
	}

}
