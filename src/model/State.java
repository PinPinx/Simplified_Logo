package model;

import view.View;

public class State {
	private Turtle myTurtle;
	private VariablesCollection myVariablesCollection;
	private CommandHistory myCommandHistory;
	
	public State(Turtle t, VariablesCollection v, CommandHistory c){
		this.myTurtle = t;
		this.myVariablesCollection = v;
		this.myCommandHistory = c;
		myTurtle.addObserver(View.getInstance().getTurtleWindow());
	}
	
	public Turtle getTurtle() {
		return this.myTurtle;
	}
	public VariablesCollection getVariablesCollection(){
		return this.myVariablesCollection;
	}
	public CommandHistory getCommandHistory(){
		return this.myCommandHistory;
	}

	
	
}
