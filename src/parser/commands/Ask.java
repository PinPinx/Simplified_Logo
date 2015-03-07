package parser.commands;

import java.util.ArrayList;
import java.util.Stack;

import model.State;
import model.TurtleMultiple;
import exceptions.BadArgumentException;
import parser.nodes.BinaryNode;
import parser.nodes.ConstantNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;

public class Ask extends BinaryNode {

	public Ask(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(nodeOne instanceof ListNode && nodeTwo instanceof ListNode)){
			throw new BadArgumentException("You need listnodes with the ask command");
		}
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException {
		TurtleMultiple turtle = myState.getTurtle();
		Stack<SyntaxNode> oldActiveStack = createOldActiveStack(turtle);
		Stack<SyntaxNode> newActiveStack = createNewActiveStack(myState);
		return runCode(newActiveStack, oldActiveStack, myState);
	}
	
	protected Stack<SyntaxNode> createNewActiveStack(State myState) throws BadArgumentException{
		ListNode referenceNode = (ListNode) nodeOne;
		Stack<SyntaxNode> newActiveStack = new Stack<SyntaxNode>();
		for (int i =0; i < referenceNode.getSize(); i++){
			newActiveStack.push(new ConstantNode(referenceNode.getNode(i).execute(myState)));
		}
		return newActiveStack;
	}
	
	protected Stack<SyntaxNode> createOldActiveStack(TurtleMultiple turtle){
		ArrayList<Integer> oldActiveList = new ArrayList<Integer>(turtle.activeTurtleIDs());
		Stack<SyntaxNode> oldActiveStack = new Stack<SyntaxNode>();
		for (Integer i : oldActiveList){
			oldActiveStack.push(new ConstantNode(i));
		}
		return oldActiveStack;
	}
	
	protected double runCode(Stack<SyntaxNode> newActiveStack, 
			Stack<SyntaxNode> oldActiveStack, State myState) throws BadArgumentException{
		myState.getTurtle().deactiveAll();
		Tell activate = tellMaker(newActiveStack);
		Tell returnState = tellMaker(oldActiveStack);
		activate.execute(myState);
		double retNumber = nodeTwo.execute(myState);
		returnState.execute(myState);
		return retNumber;
	}
	
	protected Tell tellMaker(Stack<SyntaxNode> constantList) throws BadArgumentException{
		ListNode node = new ListNode(constantList);
		Stack<SyntaxNode> inputStack = new Stack<SyntaxNode>();
		inputStack.push(node);
		return new Tell(inputStack);
	}
}
