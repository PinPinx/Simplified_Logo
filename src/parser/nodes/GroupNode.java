package parser.nodes;

import java.util.Stack;

public class GroupNode extends ListNode{
	
	public GroupNode(Stack<SyntaxNode> input){
		super(input);
		if (getSize() < 2){
			myList.add(new ConstantNode(0));
		}
	}
	
	public SyntaxNode backPop(){
		if (myList.isEmpty())
			return null;
		return myList.remove(myList.size()-1);
	}

}
