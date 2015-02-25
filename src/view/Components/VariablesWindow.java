package view.Components;

import java.util.List;

import javafx.beans.property.StringProperty;
import model.Variable;


public class VariablesWindow extends ListWindow implements VariablesObserver{


	public VariablesWindow(int w, int h) {
		super(w, h, "User Defined Variables");
	}
	

	


	@Override
	public void update(List<StringProperty> nameList,
			List<StringProperty> valueList) {
		for (int i=0; i<nameList.size(); i++){
			String name = nameList.get(i).getName();
			String value = valueList.get(i).getValue();
			VariableLabel vl = new VariableLabel(name, value);
			myList.getChildren().add(vl);	
		}
		
	}











	

}
