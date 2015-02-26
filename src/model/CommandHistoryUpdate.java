package model;

import java.util.List;

public class CommandHistoryUpdate {
	private List<String> myCommands;
	private List<String> myUDCommands;
	
	public CommandHistoryUpdate(List<String> commands){
		this.myCommands = commands;
	}
	
	public List<String> getCommandHistory(){
		return myCommands;
	}
	public List<String> getUDCommand(){
		return myUDCommands;
	}
}
