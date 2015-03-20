// This entire file is part of my masterpiece.
// Kaighn Kevlin kgk6

package model;

import parser.nodes.CommandRoot;
import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import exceptions.VariableWrongTypeException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.NumberStringConverter;

public class VariableFunction extends Variable {
	private CommandRoot myRoot;
	
	public VariableFunction(String name, CommandRoot r){
		super(name);
		this.myRoot = r;
		this.myDisplayProperty = new SimpleStringProperty(r.toString());
	}

	@Override
	public Object getValue() {
		return this.myRoot;
	}

	@Override
	public Variable clone() {
		return new VariableFunction(this.getNameProperty().getValue(), this.myRoot);
	}

	@Override
	public void setValue(String edit) throws VariableWrongTypeException {
		try {
			CommandRoot r = Model.getInstance().parseString(edit);
			this.myRoot = r;
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat
				| BadArgumentException e) {
			e.printStackTrace();
		}
	}
}
