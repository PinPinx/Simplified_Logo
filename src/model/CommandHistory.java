package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import parser.nodes.CommandRoot;

import view.Components.CommandsObserver;

public class CommandHistory implements ObservableCommand {
	private List<CommandRoot> myCommandList;
	private List<CommandsObserver> myObserverList;

	public CommandHistory() {
		this.myCommandList = new LinkedList<>();
		this.myObserverList = new ArrayList<>();
	}

	public void addCommand(CommandRoot cr) {
		myCommandList.add(cr);
		notifyObservers();
	}

	private List<String> getCommandList() {
		ArrayList<String> ret = new ArrayList<>();
		for (CommandRoot cr : myCommandList) {
			ret.add(cr.toString());
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
			o.update(getCommandList());
		}
	}
}
