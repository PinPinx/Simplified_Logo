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
		Command testCommand = new Forward(this.myState, 50);
		root.addCommand(testCommand);
		return root;
	}
}
