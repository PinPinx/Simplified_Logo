package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import parser.nodes.CommandRoot;
import parser.parser.Parser;

public class Model {
	private Parser myParser;
	private List<State> myStates;
	private State myActiveState;
	private static Model instance;
	
	private Model(){
		//TODO hide creation logic for the three following parameters of state
		this.myStates = new ArrayList<>();
		this.myParser = new Parser();
		setState(0);
		this.myActiveState = myStates.get(0);
		this.myParser.setActiveState(myActiveState);
	}
	
	public static Model getInstance(){
		if(instance == null){
			instance = new Model();
		}
		return instance;
	}
	
	public void parse(String command) throws CommandNameNotFoundException, SyntaxErrorWrongFormat, BadArgumentException{
			CommandRoot hello = myParser.parse(command);
			hello.execute(myActiveState);
			myActiveState.getCommandHistory().addCommand(hello);
	}
	
	/**
	 * Switches to the workspace with the parameter ID. All parser calls will use the active state. If the
	 * call's ID is more than the current amount of states:
	 * if it is one more than the current amount, create a new state and add it to the list of states.
	 * else, rather than throw an error, set active state to 0th state. 
	 */
	public void setState(int workspaceID){
		if(workspaceID <= myStates.size() - 1){
			myActiveState = myStates.get(workspaceID);
		}
		else if(workspaceID == myStates.size()){
			myActiveState = new State();
			myStates.add(myActiveState);
		}
		myParser.setActiveState(myActiveState);
	}
	
	/**
	 * Concatenates a long string of UDCommands and variable declarations that
	 * already exist in the workspace, for the purpose of saving.
	 */
	public String saveLibrary(int workspaceID){
		StringBuilder b = new StringBuilder();
		b.append(myStates.get(workspaceID).getCommandHistory().saveUDCommands());
		b.append(" \n");
		b.append(myStates.get(workspaceID).getVariablesCollection().saveState());
		return b.toString();
	}
}
