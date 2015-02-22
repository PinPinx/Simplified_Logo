package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VariableString extends Variable {
	private StringProperty myProperty;

	public VariableString(String name, String value) {
		super(name);
		this.myProperty = new SimpleStringProperty(value);
	}
	
	@Override
	public Object getValue() {
		return new String(myProperty.getValue());
	}

	@Override
	public Variable clone() {
		return new VariableString(this.getName(), this.myProperty.get());
	}
}
