package model;

import javafx.beans.property.IntegerProperty;


public class VariableInt extends Variable {
	private IntegerProperty myProperty;
	
	public VariableInt(String name, int value) {
		super(name);
		this.myProperty.set(value);
	}
	
	@Override
	public Object getValue() {
		return this.myProperty.getValue();
	}
	
}
