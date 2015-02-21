package commands;

import java.util.List;

public class CommandRoot implements Command {
	private List<Command> myChildren;
	private String myString;
	
	public CommandRoot(String s){
		this.myString = s;
	}

	@Override
	public void execute() {
		for(Command c : this.myChildren){
			c.execute();
		}
	}
	
	public String toString(){
		return new String(this.myString);
	}
	
}
