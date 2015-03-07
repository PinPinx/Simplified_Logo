package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exceptions.UDCommandNotFoundException;
import parser.commands.ToData;
import parser.nodes.CommandRoot;
import view.components.Observer;
import parser.nodes.VariableNode;

/**
 * Keeps account of commands already run, and user defined commands.
 */
public class CommandHistory implements Observable {
	private List<CommandRoot> myCommandList;
	private List<Observer> myObserverList;
	private List<ToData> myUDCommands;

	public CommandHistory() {
		this.myCommandList = new LinkedList<>();
		this.myUDCommands = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}

	/**
	 * To add command to command history.
	 */
	public void addCommand(CommandRoot cr) {
		myCommandList.add(cr);
		notifyObservers();
	}
	
	/**
	 * Attempts to find an existing UD command by name.
	 */
	public ToData getUDCommand(String name) throws UDCommandNotFoundException{
		for(ToData datum : myUDCommands){
			if(datum.getName().equalsIgnoreCase(name)){
				return datum;
			}
		}
		throw new UDCommandNotFoundException("UDCommand "+name+" doesn't exist.");
	}
	
	/**
	 * Adds a newly created UD command.
	 */
	public boolean addUDCommand(ToData udCommand){
		for(ToData datum : myUDCommands){
			if(datum.getName().equalsIgnoreCase(udCommand.getName())){
				return false;
			}
		}
		return myUDCommands.add(udCommand);
	}

	/**
	 * Prepares a String list of all the existing commands in the history to send to the front end.
	 * @return
	 */
	public List<String> getCommandList(){
		List<String> ret = new ArrayList<>();
		for (CommandRoot cr : myCommandList) {
			ret.add(cr.toString());
		}
		return ret;
	}
	
	/**
	 * Prepares a String list of UD command displays to send to the front end.
	 */
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
	
	/**
	 * Concatenates all UD command declarations into a single string, for the purpose of saving.
	 */
	public String saveUDCommands(){
		StringBuilder b = new StringBuilder();
		for(ToData udCommand : myUDCommands){
			b.append(udCommand.getDeclaration());
			b.append(" \n");
		}
		return b.toString();
	}
	
	@Override
	public void addObserver(Observer o) {
		myObserverList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		myObserverList.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : myObserverList) {
			o.update(new CommandHistoryUpdate(this));
		}
	}
}
