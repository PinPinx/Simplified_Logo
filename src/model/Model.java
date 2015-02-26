package model;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.nodes.CommandRoot;
import parser.parser.Parser;

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
	
	public void parse(String command) throws CommandNameNotFoundException, SyntaxErrorWrongFormat, BadArgumentException{
			CommandRoot hello = myParser.parse(command);
			hello.execute(myState);
			myState.getCommandHistory().addCommand(hello);
	}

}
