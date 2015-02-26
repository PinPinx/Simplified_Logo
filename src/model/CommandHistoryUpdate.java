package model;

import java.util.List;

public class CommandHistoryUpdate {
	private List<String> myCommands;
	private List<String> myUDCommands;
	
	public CommandHistoryUpdate(CommandHistory ch){
		this.myCommands = ch.getCommandList();
		this.myUDCommands = ch.getUDCommandList();
	}
	
	public List<String> getCommandHistory(){
		return myCommands;
	}
	public List<String> getUDCommand(){
		return myUDCommands;
	}
}
