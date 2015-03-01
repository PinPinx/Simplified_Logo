package model;

import view.View;

public class State {
	private Turtle myTurtle;
	private VariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	private ViewOptions myViewOptions;
	
	public State(Turtle t, VariablesCollection v, CommandHistory c){
		this.myTurtle = t;
		this.myVariablesCollection = v;
		this.myCommandHistory = c;
		myTurtle.addObserver(View.getInstance().getTurtleWindow());
		myVariablesCollection.addObserver(View.getInstance().getVariablesWindow());
		myCommandHistory.addObserver(View.getInstance().getCommandHistoryWindow());
		myCommandHistory.addObserver(View.getInstance().getUDCommandsWindow());
	}
	
	public Turtle getTurtle() {
		return myTurtle;
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
