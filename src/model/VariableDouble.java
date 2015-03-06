package model;

import exceptions.VariableWrongTypeException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.NumberStringConverter;

public class VariableDouble extends Variable {
	private DoubleProperty myProperty;

	public VariableDouble(String name, double value){
		super(name);
		this.myProperty = new SimpleDoubleProperty(value);
		this.myDisplayProperty = new SimpleStringProperty();
		NumberStringConverter converter = new NumberStringConverter(){
			@Override
			public Number fromString(String value){
				try{
					Integer.parseInt(value);
				} catch(NumberFormatException e){
					value = "50";
				}
				return super.fromString(value);
			}
		};
		
		myDisplayProperty.bindBidirectional(myProperty, converter);
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
			throw new VariableWrongTypeException("Edit: "+edit+"not allowed. This is a double variable. To change it's type, do so at the command line.");
		}
	}
}
