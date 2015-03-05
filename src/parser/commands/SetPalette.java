package parser.commands;

import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.InvalidViewSettingException;
import model.State;
import parser.nodes.SyntaxNode;
import parser.nodes.TrinaryNode;
import view.View;

public class SetPalette extends SyntaxNode {
	protected SyntaxNode nodeOne;
	protected SyntaxNode nodeTwo;
	protected SyntaxNode nodeThree;
	protected SyntaxNode nodeFour;
	
	public SetPalette(Stack<SyntaxNode> input) throws BadArgumentException{
		if(input.size()<4){
			throw new BadArgumentException("Command requires 3 arguments.");
		}
		nodeOne = input.pop();
		nodeTwo = input.pop();
		nodeThree = input.pop();
		nodeFour = input.pop();
	}
	
	@Override
	public double execute(State myState) throws BadArgumentException{
		int index = (int)nodeOne.execute(myState);
		int red = (int)nodeTwo.execute(myState);
		int green = (int)nodeThree.execute(myState);
		int blue = (int)nodeFour.execute(myState);

		myState.getTurtle().changeViewOptions(vo -> {try {
			vo.setPaletteB(red, green, blue);
		} catch (InvalidViewSettingException e) {
			View.getInstance().showDialog(e.getMessage());
		}});
		return index;
	}

}