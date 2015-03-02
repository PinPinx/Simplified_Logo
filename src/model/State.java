package model;

import view.View;

public class State {
	private TurtleMultiple myTurtleMultiple;
	private VariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	private ViewOptions myViewOptions;
	
	public State(TurtleMultiple t, VariablesCollection v, CommandHistory c){
		this.myTurtleMultiple = t;
		this.myVariablesCollection = v;
		this.myCommandHistory = c;
		myTurtleMultiple.addObserver(View.getInstance().getTurtleWindow());
		myVariablesCollection.addObserver(View.getInstance().getVariablesWindow());
		myCommandHistory.addObserver(View.getInstance().getCommandHistoryWindow());
		myCommandHistory.addObserver(View.getInstance().getUDCommandsWindow());
	}
	
	public Turtle getTurtle() {
		return myTurtleMultiple;
	}
	public VariablesCollection getVariablesCollection(){
		return myVariablesCollection;
	}
	public CommandHistory getCommandHistory(){
		return myCommandHistory;
	}
	public ViewOptions getViewOptions(){
		return myViewOptions;
	}

	
	
}
