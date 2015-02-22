package parser.commands;

import java.util.ArrayList;
import java.util.List;

import Exceptions.BadArgumentException;

public class CommandRoot implements Command {
	private List<Command> myChildren;
	private String myString;
	
	public CommandRoot(String s){
		this.myString = s;
		this.myChildren = new ArrayList<>();
	}

	public void addCommand(Command c){
		this.myChildren.add(c);
	}
	
	@Override
	public void execute() throws BadArgumentException {
		for(Command c : this.myChildren){
			c.execute();
		}
	}
	
	public String toString(){
		return new String(this.myString);
	}
	
}
