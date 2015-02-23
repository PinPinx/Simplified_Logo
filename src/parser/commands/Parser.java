package parser.commands;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Stack;

import parser.nodes.CommandRoot;
import parser.nodes.ConstantNode;
import parser.nodes.ListNode;
import parser.nodes.SyntaxNode;
import parser.nodes.VariableNode;
import Exceptions.CommandNameNotFoundException;
import model.State;

public class Parser {
	private State myState;
	private Regex myRegex;
	
	public Parser(State s){
		this.myState = s;
	}
	
	public CommandRoot parse(String command) throws CommandNameNotFoundException {
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
					cls = Class.forName("parser.commands." + className + ".java");
					
				} catch (ClassNotFoundException e) {throw new CommandNameNotFoundException();} //TODO never happens?
				
				try {
					inputStack.peek().push((SyntaxNode) cls.getConstructors()[0].newInstance(inputStack));
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
			case LISTSTART:
				Stack<SyntaxNode> listStack = inputStack.pop();
				inputStack.peek().push(new ListNode(listStack));
			case OTHER: default:
				break; //TODO throw something
			}
		}
		return new CommandRoot(command, inputStack.peek());
	}
}
