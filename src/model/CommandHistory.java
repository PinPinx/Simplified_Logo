package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import commands.Command;
import commands.CommandRoot;
import view.Observer;

public class CommandHistory implements Observable {
	private List<CommandRoot> myCommandList;
	private List<Observer> myObserverList;
	
	public CommandHistory(){
		this.myCommandList = new LinkedList<>();
		this.myObserverList = new ArrayList<>();
	}
	
	public void addCommand(CommandRoot cr){
		this.myCommandList.add(cr);
		notifyObservers();
	}
	
	public List<String> getCommandList(){
		ArrayList<String> ret = new ArrayList<>();
		for(CommandRoot cr : this.myCommandList){
			ret.add(cr.toString());
		}
		return ret;
	}
	
	@Override
	public void addObserver(Observer o) {
		this.myObserverList.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.myObserverList.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : this.myObserverList){
			o.update(getCommandList());
		}
	}
}
