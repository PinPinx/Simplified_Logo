package parser.commands;

import java.util.Stack;

import model.State;
import model.TurtleMultiple;
import exceptions.BadArgumentException;
import exceptions.TurtleNotFoundException;
import parser.nodes.ConstantNode;
import parser.nodes.SyntaxNode;

public class AskWith extends Ask {

	public AskWith(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
	}

	@Override
	protected Stack<SyntaxNode> createNewActiveStack(State myState) throws BadArgumentException{
		TurtleMultiple turtle = myState.getTurtle();
		Stack<SyntaxNode> newActiveStack = new Stack<SyntaxNode>();
		for (int i = 0; i < turtle.numTurtles(); i++){
			turtle.deactiveAll();
			try {
				turtle.getTurtleSingle(i).setInactive(false);
			} catch (TurtleNotFoundException e) {
				continue;
			}
			if (nodeOne.execute(myState)!= 0){
				newActiveStack.push(new ConstantNode(i));
			}
		}
		return newActiveStack;
	}
}
