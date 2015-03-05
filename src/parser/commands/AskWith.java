package parser.commands;

import java.util.ArrayList;
import java.util.Stack;

import model.State;
import model.Turtle;
import model.TurtleMultiple;
import exceptions.BadArgumentException;
import exceptions.TurtleNotFoundException;
import parser.nodes.BinaryNode;
import parser.nodes.ConstantNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;

public class AskWith extends Ask {
//TODO: You can use inheritance to make this the constructor of Ask as well
	public AskWith(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}
	
//TODO: A LOT OF DUPLICATED CODE WITH ASK
	@Override
	public double execute(State myState) throws BadArgumentException {
		TurtleMultiple turtle = myState.getTurtle();
		ArrayList<Integer> oldActiveList = new ArrayList<Integer>(turtle.activeTurtleIDs());
		Stack<SyntaxNode> oldActiveStack = new Stack<SyntaxNode>();
		Stack<SyntaxNode> newActiveStack = new Stack<SyntaxNode>();
		for (Integer i : oldActiveList){
			oldActiveStack.push(new ConstantNode(i));
		}
		for (int i = 0; i < turtle.numTurtles(); i++){
			turtle.deactiveAll();
			try {
				myState.getTurtle().getTurtleSingle(i).setInactive(false);
			} catch (TurtleNotFoundException e) {
				continue;
			}
			if (nodeOne.execute(myState)!=0){
				newActiveStack.push(new ConstantNode(i));
			}
		}
		Tell activate = tellMaker(newActiveStack);
		Tell returnState = tellMaker(oldActiveStack);
		activate.execute(myState);
		double retNumber = nodeTwo.execute(myState);
		returnState.execute(myState);
		return retNumber;
	}

}
