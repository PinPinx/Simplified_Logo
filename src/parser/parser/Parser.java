package parser.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.commands.ToData;
import parser.commands.ToInstance;
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
				Class<?> cls;
				try {
					className = Regex.getInstance().getCommandType(commandStream[i]);
					String str = "parser.commands." + className;
					cls = Class.forName(str);
				} catch (ClassNotFoundException | CommandNameNotFoundException e) {
						if(i>0 && commandStream[i-1].equalsIgnoreCase("to")){
							inputStack.peek().push(new ToData(commandStream[i], inputStack.peek()));
							i--;
							continue;
						}
						else if(myState.getCommandHistory().getUDCommand(commandStream[i])!=null){
							ToData data = myState.getCommandHistory().getUDCommand(commandStream[i]);
							inputStack.peek().push(new ToInstance(data, inputStack.peek()));
							continue;
						}
						else 
							throw new CommandNameNotFoundException();
				} //TODO never happens?
				
				try {
					inputStack.peek().push((SyntaxNode) cls.getConstructors()[0].newInstance(inputStack.peek()));
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException | SecurityException e) {
					System.out.println("We fail at: " + commandStream[i]);
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
