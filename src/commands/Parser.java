package commands;

import java.util.Collections;
import java.util.Stack;

import Exceptions.CommandNameNotFoundException;
import model.State;

public class Parser {
	private State myState;
	private Regex myRegex;

	public Parser(State s){
		this.myState = s;
		this.myRegex = new Regex();
	}

	public CommandRoot parse(String command) throws CommandNameNotFoundException{
		String[] commandStream = command.split("\\p{Space}");
		CommandRoot root = new CommandRoot(command);
		Stack<String> commandStringStack = new Stack<>();
		for(int i = commandStream.length-1; i >= 0; i--){
			commandStringStack.push(commandStream[i]);
		}

		parsePart(commandStringStack);

		Command testCommand;
		if(command.length()>5){
			testCommand = new Forward(this.myState, 50);
		}
		else{
			testCommand = new Left(this.myState, 90);
		}
		root.addCommand(testCommand);
		return root;
	}

	public void parsePart(Stack<String> stack) throws CommandNameNotFoundException{
		int numArgs;
		Class<?> cls;
		try {
			cls = Class.forName(stack.pop());
			numArgs = cls.getConstructors()[0].getParameterCount() - 1;
		} catch (ClassNotFoundException e) {
			throw new CommandNameNotFoundException();
		}	
		
		for(int i = 0; i < numArgs; i++){
			parsePart(stack);
		}
	}
}
