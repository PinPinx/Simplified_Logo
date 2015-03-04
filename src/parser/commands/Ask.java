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
		if (!(nodeOne instanceof ListNode && nodeTwo instanceof ListNode))
			throw new BadArgumentException("You need listnodes with the ask command");
	}
	//TODO: CLEAN THIS SHIT UP YO
	@Override
	public double execute(State myState) throws BadArgumentException {
		TurtleMultiple turtle = myState.getTurtle();
		ArrayList<Integer> oldActiveList = new ArrayList<Integer>(turtle.activeTurtleIDs());
		turtle.deactiveAll();
		Stack<SyntaxNode> oldActiveStack = new Stack<SyntaxNode>();
		Stack<SyntaxNode> newActiveStack = new Stack<SyntaxNode>();
		for (Integer i : oldActiveList){
			oldActiveStack.push(new ConstantNode(i));
		}
		for (int i =0; i < ((ListNode) nodeOne).getSize(); i++){
			newActiveStack.push(new ConstantNode(i));
		}
		Tell activate = new Tell(newActiveStack);
		Tell returnState = new Tell(oldActiveStack);
		activate.execute(myState);
		double retNumber = nodeTwo.execute(myState);
		returnState.execute(myState);
		return retNumber;
	}

}
