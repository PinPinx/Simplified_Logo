package model;

import exceptions.VariableWrongTypeException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.converter.NumberStringConverter;

public class VariableDouble extends Variable {
	private DoubleProperty myProperty;

	public VariableDouble(String name, double value){
		super(name);
		this.myProperty = new SimpleDoubleProperty(value);
		this.myDisplayProperty = new SimpleStringProperty();
		myDisplayProperty.bindBidirectional(myProperty, new NumberStringConverter());
	}

	@Override
	public Object getValue() {
		return myProperty.getValue();
	}

	@Override
	public Variable clone() {
		return new VariableDouble(this.getNameProperty().getValue(), this.myProperty.get());
	}

	@Override
	public void setValue(String edit) throws VariableWrongTypeException {
		try{
			double d = Double.parseDouble(edit);
			this.myProperty.set(d);
		} catch (NumberFormatException e){
			throw new VariableWrongTypeException();
		}
	}
}
