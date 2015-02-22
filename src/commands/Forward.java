package commands;

import model.Coordinates;
import model.State;

public class Forward extends PrimitiveCommand {
	private double parameter;
	
	public Forward(State s, double parameter) {
		super(s);
		this.parameter = parameter;
	}

	@Override
	public void execute() {
		double angle = this.myState.getTurtle().getAngle().getAngleValueInRadians();
		double deltaX, deltaY;
		deltaX = Math.cos(angle)*this.parameter;
		deltaY = Math.sin(angle)*this.parameter;
		Coordinates displacement = new Coordinates(deltaX, deltaY);
		this.myState.getTurtle().addCoordinates(displacement);
	}

	
}
