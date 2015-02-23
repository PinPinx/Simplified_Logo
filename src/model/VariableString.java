package model;

import Exceptions.VariableWrongTypeException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.converter.NumberStringConverter;

public class VariableString extends Variable {
	private StringProperty myProperty;

	public VariableString(String name, String value) {
		super(name);
		this.myProperty = new SimpleStringProperty(value);
		this.myDisplayProperty = new SimpleStringProperty();
		myDisplayProperty.bindBidirectional(myProperty);
	}
	
	@Override
	public Object getValue() {
		return new String(myProperty.getValue());
	}

	@Override
	public Variable clone() {
		return new VariableString(this.getName(), this.myProperty.get());
	}

	@Override
	public void setValue(String edit) throws VariableWrongTypeException {
		try{
			myProperty.set(edit);
		} catch (NumberFormatException e){
			throw new VariableWrongTypeException();
		}
	}
}
