package parser.commands;

import java.util.Stack;

import model.State;
import model.TurtleMultiple;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.UnaryNode;
import parser.nodes.VariableNode;
import exceptions.BadArgumentException;
import exceptions.TurtleNotFoundException;

public class Tell extends UnaryNode{

	private ListNode listReference;
	
	public Tell(Stack<SyntaxNode> input) throws BadArgumentException {
		super(input);
		if (!(referenceNode instanceof ListNode))
			throw new BadArgumentException("A non-list input to tell");
		listReference = (ListNode) referenceNode;
	}

	//TODO: Duplicate code with DoTimes
	@Override
	public double execute(State myState) throws BadArgumentException {
		TurtleMultiple t = myState.getTurtle();
		t.deactiveAll();
		int turtleID = 0;
		for(int i = 0; i < listReference.getSize(); i++){
			turtleID = (int) listReference.getNode(i).execute(myState);
			try{
				myState.getTurtle().getTurtleSingle(turtleID).setInactive(false);;
			} catch (TurtleNotFoundException e){
				myState.getTurtle().addNewTurtle(turtleID);
			}
		}
		return turtleID;			
	}
}
