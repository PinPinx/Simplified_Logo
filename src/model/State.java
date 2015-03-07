package model;

import view.View;

public class State {
	private TurtleMultiple myTurtleMultiple;
	private VariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	
	public State(){
		this.myTurtleMultiple = new TurtleMultiple();
		this.myVariablesCollection = new VariablesCollection();
		this.myCommandHistory = new CommandHistory();
		myTurtleMultiple.addObserver(View.getInstance().getTurtleWindow());
		myVariablesCollection.addObserver(View.getInstance().getVariablesWindow());
		myCommandHistory.addObserver(View.getInstance().getCommandHistoryWindow());
		myCommandHistory.addObserver(View.getInstance().getUDCommandsWindow());
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

	
	
}
