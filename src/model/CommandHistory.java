package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import parser.commands.MakeUserInstruction;
import parser.nodes.CommandRoot;
import view.Components.CommandsObserver;

public class CommandHistory implements ObservableCommand {
	private List<CommandRoot> myCommandList;
	private List<CommandsObserver> myObserverList;
	private List<MakeUserInstruction> myUDCommands;

	public CommandHistory() {
		this.myCommandList = new LinkedList<>();
		this.myUDCommands = new ArrayList<>();
		this.myObserverList = new ArrayList<>();
	}

	public void addCommand(CommandRoot cr) {
		myCommandList.add(cr);
		notifyObservers();
	}
	
	public void addUDCommand(MakeUserInstruction udCommand){
		myUDCommands.add(udCommand);
	}

	private CommandHistoryUpdate getCommandUpdate() {
		ArrayList<String> ret = new ArrayList<>();
		for (CommandRoot cr : myCommandList) {
			ret.add(cr.toString());
		}
		return new CommandHistoryUpdate((List<String>) Collections.unmodifiableCollection(ret));
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
			o.update(getCommandUpdate());
		}
	}
}
