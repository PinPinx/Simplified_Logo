package commands;

import model.State;

public abstract class PrimitiveCommand implements Command{
	protected State myState;
	
	public PrimitiveCommand(State s){
		this.myState = s;
	}
}
