package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import exceptions.UDCommandNotFoundException;
import parser.commands.ToData;
import parser.nodes.CommandRoot;
import parser.nodes.VariableNode;
import view.Components.CommandsObserver;

public class CommandHistory implements ObservableCommand {
	private List<CommandRoot> myCommandList;
	private List<CommandsObserver> myObserverList;
	private List<ToData> myUDCommands;

	public CommandHistory() {
		this.myCommandList = new LinkedList<>();
		this.myUDCommands = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}

	public void addCommand(CommandRoot cr) {
		myCommandList.add(cr);
		notifyObservers();
	}
	
	//TODO: Duplicated code
	public ToData getUDCommand(String name) throws UDCommandNotFoundException{
		for(ToData datum : myUDCommands){
			if(datum.getName().equalsIgnoreCase(name)){
				return datum;
			}
		}
		throw new UDCommandNotFoundException();
	}
	public boolean addUDCommand(ToData udCommand){
		for(ToData datum : myUDCommands){
			if(datum.getName().equalsIgnoreCase(udCommand.getName())){
				return false;
			}
		}
		return myUDCommands.add(udCommand);
	}


	public List<String> getCommandList(){
		List<String> ret = new ArrayList<>();
		for (CommandRoot cr : myCommandList) {
			ret.add(cr.toString());
		}
		return ret;
	}
	
	public List<String> getUDCommandList(){
		List<String> ret = new ArrayList<>();
		for (ToData datum : myUDCommands) {
			StringBuilder b = new StringBuilder();
			b.append(datum.getName());
			for(int i = 0; i < datum.getVarList().getSize(); i++){
				b.append(" ");
				b.append(((VariableNode)datum.getVarList().getNode(i)).getName());
			}
			ret.add(b.toString());
		}
		return ret;
	}
	
	@Override
	public void addObserver(CommandsObserver o) {
		myObserverList.add(o);
	}

	@Override
	public void removeObserver(CommandsObserver o) {
		myObserverList.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (CommandsObserver o : myObserverList) {
			o.update(new CommandHistoryUpdate(this));
		}
	}
}
