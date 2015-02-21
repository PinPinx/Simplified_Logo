package model;

import javafx.beans.property.DoubleProperty;

public class VariableDouble extends Variable {
	private DoubleProperty myProperty;
	
	public VariableDouble(String name, double value){
		super(name);
		this.myProperty.set(value);
	}
	
	@Override
	public Object getValue() {
		return this.myProperty.getValue();
	}

}
