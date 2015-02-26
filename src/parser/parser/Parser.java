package parser.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.nodes.*;
import model.State;

public class Parser {
	private State myState;
	private Regex myRegex;
	
	public Parser(State s){
		this.myState = s;
	}
	
	public CommandRoot parse(String command) throws CommandNameNotFoundException, SyntaxErrorWrongFormat {
		String[] commandStream = command.split("\\p{Space}");
		Stack<Stack<SyntaxNode>> inputStack = new Stack<Stack<SyntaxNode>>();
		inputStack.push(new Stack<>());
		for(int i = commandStream.length-1; i >= 0; i--){
			switch(Regex.getInstance().getType(commandStream[i])){
			case COMMAND:
				String className;
				className = Regex.getInstance().getCommandType(commandStream[i]);
				Class<?> cls;
				try {
					String str = "parser.commands." + className;
					cls = Class.forName(str);
				} catch (ClassNotFoundException e) {
//					if(i>0 && Regex.getInstance().getCommandType(commandStream[i-1]).equalsIgnoreCase("To")){
//						inputStack.peek().push(new CommandRoot(commandStream[i], inputStack.peek()));
//						i--;
//					}
					throw new CommandNameNotFoundException();} //TODO never happens?
				
				try {
					inputStack.peek().push((SyntaxNode) cls.getConstructors()[0].newInstance(inputStack.peek()));
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException | SecurityException e) {
					throw new CommandNameNotFoundException();
				}			
				
			case COMMENT:
				break;
			case CONSTANT:
				inputStack.peek().push(new ConstantNode(commandStream[i]));
				break;
			case VARIABLE:
				inputStack.peek().push(new VariableNode(commandStream[i]));
				break;
			case GROUPEND: //TODO
				break;
			case GROUPSTART: //TODO
				break;
			case LISTEND:
				inputStack.push(new Stack<SyntaxNode>());
				break;
			case LISTSTART:
				Stack<SyntaxNode> listStack = inputStack.pop();
				inputStack.peek().push(new ListNode(listStack));
				break;
			case OTHER: default:
				throw new SyntaxErrorWrongFormat();
			}
		}
		return new CommandRoot(command, inputStack.peek());
	}
}
