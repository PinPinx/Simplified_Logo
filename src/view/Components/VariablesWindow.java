package view.Components;

import java.util.List;

import model.Variable;

public class VariablesWindow extends ListWindow {


	public VariablesWindow(int w, int h) {
		super(w, h, "User Defined Variables");
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
			String value = (String) v.getValue();
			//addButton(b);
		}
	}

}
