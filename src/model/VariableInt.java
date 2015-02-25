package model;

import Exceptions.VariableWrongTypeException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.NumberStringConverter;


public class VariableInt extends Variable {
	private IntegerProperty myProperty;
	
	public VariableInt(String name, int value) {
		super(name);
		this.myProperty = new SimpleIntegerProperty(value);
		this.myDisplayProperty = new SimpleStringProperty();
		myDisplayProperty.bindBidirectional(myProperty, new NumberStringConverter());
	}
	
	@Override
	public Object getValue() {
		return this.myProperty.getValue();
	}

	@Override
	public Variable clone() {
		return new VariableInt(this.getNameProperty().getValue(),this.myProperty.get());
	}

	@Override
	public void setValue(String edit) throws VariableWrongTypeException {
		try{
			int i = Integer.parseInt(edit);
			this.myProperty.set(i);
		} catch (NumberFormatException e){
			throw new VariableWrongTypeException();
		}
	}
	
}
