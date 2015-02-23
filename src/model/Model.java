package model;

import parser.commands.Parser;
import Exceptions.BadArgumentException;
import Exceptions.CommandNameNotFoundException;

public class Model {
	private Parser myParser;
	private State myState;
	
	private static Model instance;
	
	private Model(){
		//TODO hide creation logic for the three following parameters of state
		this.myState = new State(new TurtleSingle(), new VariablesCollection(), new CommandHistory());
		this.myParser = new Parser(this.myState);
	}
	
	public static Model getInstance(){
		if(instance == null){
			instance = new Model();
		}
		return instance;
	}
	
	public void parse(String command) throws CommandNameNotFoundException{
		try {
			myParser.parse(command).execute(myState);
		} catch (BadArgumentException e) {
			e.printStackTrace();
		}
	}

}
