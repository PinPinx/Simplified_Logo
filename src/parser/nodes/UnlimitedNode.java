package parser.nodes;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import exceptions.BadArgumentException;

//TODO: This may be able to extend BinaryNode if we separate initialization and the constructor
public abstract class UnlimitedNode extends SyntaxNode{

	protected SyntaxNode nodeOne;
	protected SyntaxNode nodeTwo;
	
	public UnlimitedNode(Stack<SyntaxNode> input) throws BadArgumentException {
		if (input.peek() instanceof GroupNode){
			GroupNode myGroup = (GroupNode) input.pop();
			Stack<SyntaxNode> groupStack = new Stack<SyntaxNode>();
			while (myGroup.getSize()>0){
				//TODO: CHOOSE BETWEEN THESE TWO WAYS OF DOING THINGS
				groupStack.push(myGroup.backPop());
				if (groupStack.peek() instanceof GroupNode){
					//convertAndPush(groupStack);
					groupStack.push((ListNode) groupStack.pop());
				}
			}
			while(groupStack.size()>2){
				convertAndPush(groupStack);
			}
			nodeOne = groupStack.pop();
			nodeTwo = groupStack.pop();
		}
		else {
			nodeOne = input.pop();
			nodeTwo = input.pop();
		}
	}
	
	private void convertAndPush(Stack<SyntaxNode> input) throws BadArgumentException{
		try {
			input.push((UnlimitedNode) this.getClass().getConstructors()[0].newInstance(input));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			throw new BadArgumentException("Something went wrong with groups");
		}
	}
}
