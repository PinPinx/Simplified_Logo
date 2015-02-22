package commands;

import model.State;

public class Parser {
	private State myState;
	
	public Parser(State s){
		this.myState = s;
	}
	
	public CommandRoot parse(String command){
		//TODO
		CommandRoot root = new CommandRoot(command);
		
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
}
