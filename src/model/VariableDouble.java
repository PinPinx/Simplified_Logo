package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class VariableDouble extends Variable {
	private DoubleProperty myProperty;
	
	public VariableDouble(String name, double value){
		super(name);
		this.myProperty = new SimpleDoubleProperty(value);
	}
	
	@Override
	public Object getValue() {
		return this.myProperty.getValue();
	}

	@Override
	public Variable clone() {
		return new VariableDouble(this.getName(), this.myProperty.get());
	}

}
