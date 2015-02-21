package model;

public class TurtleSingle implements Turtle {
	private Coordinates myCoordinates;
	private Angle myAngle;
	private boolean isPenUp, isHidden, isActive;
	
	@Override
	public Angle getAngle() {
		return this.myAngle;
	}

	@Override
	public Coordinates getPoint() {
		return this.myCoordinates;
	}

	@Override
	public void addAngle(Angle a) {
		this.myAngle.addAngleValue(a);
	}

	@Override
	public void setAngle(Angle a) {
		this.myAngle = new Angle(a);
	}

	@Override
	public void addCoordinates(Coordinates c) {
		// TODO Auto-generated method stub
		this.myCoordinates.addCoordinates(c);
	}

	@Override
	public void setCoordinates(Coordinates c) {
		this.myCoordinates = c;
	}

	@Override
	public void setHidden(boolean b) {
		this.isHidden = b;
	}

	@Override
	public void setActive(boolean b) {
		this.isActive = b;
	}

	@Override
	public void setPenUp(boolean b) {
		this.isPenUp = b;
	}

	@Override
	public boolean getHidden() {
		return this.isHidden;
	}

	@Override
	public boolean getActive() {
		return this.isActive;
	}

	@Override
	public boolean penUp() {
		return this.isPenUp;
	}
	
}
