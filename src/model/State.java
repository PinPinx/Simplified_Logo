package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import view.View;

public class State {
	private TurtleMultiple myTurtleMultiple;
	private VariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	private ViewOptions myViewOptions;
	
	public State(){
		this.myTurtleMultiple = new TurtleMultiple();
		this.myVariablesCollection = new VariablesCollection();
		this.myCommandHistory = new CommandHistory();
		this.myViewOptions = new ViewOptions();
		myVariablesCollection.addObserver(View.getInstance().getVariablesWindow());
		myCommandHistory.addObserver(View.getInstance().getCommandHistoryWindow());
		myCommandHistory.addObserver(View.getInstance().getUDCommandsWindow());
		myViewOptions.addObserver(View.getInstance().getTurtleWindow());
	}
	
	public TurtleMultiple getTurtle() {
		return myTurtleMultiple;
	}
	public IVariablesCollection getVariablesCollection(){
		return myVariablesCollection;
	}
	public CommandHistory getCommandHistory(){
		return myCommandHistory;
	}
	public ViewOptions getViewOptions(){
		return myViewOptions;
	}

}
