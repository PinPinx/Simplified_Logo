package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pen implements PenUpdate {
	private int turtleID;
	private IntegerProperty penColorID;
	private DoubleProperty penSize;
	
	public Pen(int ID){
		penColorID = new SimpleIntegerProperty(0);
		penSize = new SimpleDoubleProperty(5.0);
	}
	/**
	 * Returns a binded copy of the PenColorID.
	 */
	public IntegerProperty getPenColorIDProperty() {
		IntegerProperty copy = new SimpleIntegerProperty(penColorID.get());
		penColorID.bindBidirectional(copy);
		return copy;
	}
	
	/**
	 * Returns a binded copy of the PenSize.
	 */
	public DoubleProperty getPenSizeProperty() {
		DoubleProperty copy = new SimpleDoubleProperty(penSize.get());
		penSize.bindBidirectional(copy);
		return copy;
	}

	public void setPenColorID(int penColorID) {
		this.penColorID.set(penColorID);
	}
	public void setPenSize(double penSize) {
		this.penSize.set(penSize);;
	}
	@Override
	public int getTurtleID() {
		return turtleID;
	}

}
