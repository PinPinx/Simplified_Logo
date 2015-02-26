package model;

import parser.parser.GeneralType;
import parser.parser.Regex;
import view.View;
import exceptions.VariableWrongTypeException;
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
		return new VariableString(this.getNameProperty().getValue(), this.myProperty.get());
	}

	@Override
	public void setValue(String edit) throws VariableWrongTypeException {	
		if(Regex.getInstance().getType(edit) == GeneralType.CONSTANT){
			View.getInstance().showDialog("The edited value looks like a variable, but since this is a String variable, the edit will be interpreted as a string. Use the command line to change types.");
		}
		myProperty.set(edit);
	}
}
