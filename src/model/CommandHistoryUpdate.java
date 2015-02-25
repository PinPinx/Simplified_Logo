package model;

import java.util.List;

public class CommandHistoryUpdate {
	private List<String> myCommands;
	
	public CommandHistoryUpdate(List<String> commands){
		this.myCommands = commands;
	}
	
	public List<String> getCommandHistory(){
		return myCommands;
	}
}
