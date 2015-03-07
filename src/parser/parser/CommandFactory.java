package parser.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import exceptions.CommandNameNotFoundException;
import parser.nodes.SyntaxNode;

public class CommandFactory {

	private static CommandFactory instance;

	private CommandFactory(){
	
	}
	
	public static CommandFactory getInstance(){
		if(instance == null){
			instance = new CommandFactory();
		}
		return instance;
	}
	
	public SyntaxNode createCommand(String command, Stack<SyntaxNode> input) throws CommandNameNotFoundException, ClassNotFoundException{
		String className;
		Class<?> cls;
		className = Regex.getInstance().getCommandType(command);
		String str = "parser.commands." + className;
		cls = Class.forName(str);
		try {
			return (SyntaxNode) cls.getConstructors()[0].newInstance(input);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
				throw new CommandNameNotFoundException(e.getMessage()+" message");
		}					
	}
}