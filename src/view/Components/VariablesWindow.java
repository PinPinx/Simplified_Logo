package view.Components;

import java.util.List;

import javafx.beans.property.StringProperty;
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
	

	@Override
	public void update(Object updateObject1, Object updateObject2) {
		List<StringProperty> nameList = (List<StringProperty>) updateObject1;
		List<StringProperty> valueList = (List<StringProperty>) updateObject2;
		for (int i=0; i<nameList.size(); i++){
			String name = nameList.get(i).getName();
			String value = valueList.get(i).getValue();
			VariableLabel vl = new VariableLabel(name, value);
			myList.getChildren().add(vl);	
		}
		
	}

}
