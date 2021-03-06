package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import view.View;

/**
 * Class that contains all the information that can change due to user actions.
 */
public class State {
	private TurtleMultiple myTurtleMultiple;
	private IVariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	private ViewOptions myViewOptions;
	
	public State(){
		this.myTurtleMultiple = new TurtleMultiple();
		this.myVariablesCollection = new ScopedVariablesCollection();
		this.myCommandHistory = new CommandHistory();
		this.myViewOptions = new ViewOptions();
		myVariablesCollection.addObserver(View.getInstance().getVariablesWindow());
		myCommandHistory.addObserver(View.getInstance().getCommandHistoryWindow());
		myCommandHistory.addObserver(View.getInstance().getUDCommandsWindow());
		myViewOptions.addObserver(View.getInstance().getTurtleWindow());
		myViewOptions.initialize();
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
