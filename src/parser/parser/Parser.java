package parser.parser;

import java.util.Stack;

import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.commands.ToData;
import parser.nodes.*;
import model.State;

public class Parser {
	private State myActiveState;
	
	public void setActiveState(State s){
		myActiveState = s;
	}
	
	public CommandRoot parse(String command) throws CommandNameNotFoundException, SyntaxErrorWrongFormat {
		String[] commandStream = command.split("\\p{Space}");
		Stack<Stack<SyntaxNode>> inputStack = new Stack<Stack<SyntaxNode>>();
		inputStack.push(new Stack<>());
		for(int i = commandStream.length-1; i >= 0; i--){
			switch(Regex.getInstance().getType(commandStream[i])){
			case COMMAND:
			try {
				inputStack.peek().push(CommandFactory.getInstance().createCommand(commandStream[i], inputStack.peek()));
			} catch (ClassNotFoundException | CommandNameNotFoundException e) {
						inputStack.peek().push(ToFactory.getInstance().
								createTo(commandStream, myActiveState, inputStack.peek(), i));
						if (inputStack.peek().peek() instanceof ToData)
							i--;
			}
			break;
			case COMMENT:
				break;
			case CONSTANT:
				inputStack.peek().push(new ConstantNode(commandStream[i]));
				break;
			case VARIABLE:
				inputStack.peek().push(new VariableNode(commandStream[i]));
				break;
			case LISTEND: case GROUPEND:
				inputStack.push(new Stack<SyntaxNode>());
				break;
			case GROUPSTART:
				Stack<SyntaxNode> groupStack = inputStack.pop();
				inputStack.peek().push(new GroupNode(groupStack));
				break;
			case LISTSTART: 
				Stack<SyntaxNode> listStack = inputStack.pop();
				inputStack.peek().push(new ListNode(listStack));
				break;
			case OTHER: default:
				if(commandStream[i].length()==0){break;}
				throw new SyntaxErrorWrongFormat("Input "+commandStream[i]+" is not valid according to our syntax.");
			}
		}
		return new CommandRoot(command, inputStack.peek());
	}
}
