package view.Components;

import java.util.List;

import view.Button.*;
import model.Variable;

public class VariablesWindow extends viewButtonList{
	
	public VariablesWindow(){
		setDimensions();
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(List<Variable> variableList){
		for (Variable v : variableList){
			String name = v.getName();
			String value = v.getValue();
			variablesButton b = new variablesButton(name, value);
			addButton(b);
		}
	}

}
