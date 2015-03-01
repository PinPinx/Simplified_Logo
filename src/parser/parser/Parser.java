package parser.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Stack;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import exceptions.UDCommandNotFoundException;
import parser.commands.ToData;
import parser.commands.ToInstance;
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
						try{
							ToData data = myActiveState.getCommandHistory().getUDCommand(commandStream[i]);
							inputStack.peek().push(new ToInstance(data, inputStack.peek()));
							continue;
						} catch (UDCommandNotFoundException e1){
							throw new CommandNameNotFoundException("Command "+commandStream[i]+" is neither a valid preset command nor user defined command.");
						}
				}
				
				try {
					inputStack.peek().push((SyntaxNode) cls.getConstructors()[0].newInstance(inputStack.peek()));
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException
						| InvocationTargetException | SecurityException e) {
						throw new CommandNameNotFoundException(e.getMessage()+" message");
//					throw new CommandNameNotFoundException("Command "+commandStream[i]+" has broken our program. Anarchy!");
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
				throw new SyntaxErrorWrongFormat("Input "+commandStream[i]+" is not valid according to our syntax.");
			}
		}
		return new CommandRoot(command, inputStack.peek());
	}
}
