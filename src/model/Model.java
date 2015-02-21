package model;

import commands.Parser;

public class Model {
	private Parser myParser;
	private State myState;
	
	public Model(){
		//TODO hide creation logic for the three following parameters of state
		this.myState = new State(new TurtleSingle(), new VariablesCollection(), new CommandHistory());
		this.myParser = new Parser(this.myState);
	}
	
	public void parse(String command){
		this.myParser.parse(command);
	}
}
