package parser.nodes;

import model.State;

//import java.util.Stack;

public class ConstantNode extends SyntaxNode {

	private double myValue;
	
	public ConstantNode(int input){
		myValue=input;
	}
	
	public ConstantNode(String input){
		try{
			myValue= Double.parseDouble(input);
		}
		catch(NumberFormatException e){
			throw e;
		}
	}
	
	public ConstantNode(double input){
		myValue=input;
	}
	/*public ConstantNode(Stack<SyntaxNode> input){
		myValue= input.pop().interpret();
	}*/
	
	@Override
	public double execute(State myState) {
		return myValue;
	}

}
