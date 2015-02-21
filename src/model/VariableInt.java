package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class VariableInt extends Variable {
	private IntegerProperty myProperty;
	
	public VariableInt(String name, int value) {
		super(name);
		this.myProperty = new SimpleIntegerProperty(value);
	}
	
	@Override
	public Object getValue() {
		return this.myProperty.getValue();
	}

	@Override
	public Variable clone() {
		return new VariableInt(this.getName(),this.myProperty.get());
	}
	
}
