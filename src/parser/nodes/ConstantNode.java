package parser.nodes;

//import java.util.Stack;

public class ConstantNode extends SyntaxNode {

	private int myValue;
	
	public ConstantNode(int input){
		myValue=input;
	}
	
	public ConstantNode(String input){
		try{
			myValue= Integer.parseInt(input);
		}
		catch(NumberFormatException e){
			throw e;
		}
	}
	
	/*public ConstantNode(Stack<SyntaxNode> input){
		myValue= input.pop().interpret();
	}*/
	
	@Override
	public double execute() {
		return myValue;
	}

}
