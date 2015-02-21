package model;

import javafx.beans.property.StringProperty;

public class VariableString extends Variable {
	private StringProperty myValue;

	public VariableString(String name, String value) {
		super(name);
		
	}
	
	@Override
	public Object getValue() {
		return new String(myValue.getValue());
	}
}
