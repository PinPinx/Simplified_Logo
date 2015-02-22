package commands;

import model.Angle;
import model.Coordinates;
import model.State;

public class Left extends PrimitiveCommand {
	private double parameter;
	
	public Left(State s, double parameter) {
		super(s);
		this.parameter = parameter;
	}

	@Override
	public void execute() {
		this.myState.getTurtle().addAngle(new Angle(this.parameter));
	}

	
}
