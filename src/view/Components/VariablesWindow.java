package view.Components;

import java.util.List;

import javafx.beans.property.StringProperty;
import model.Variable;
import model.VariablesCollectionUpdate;


public class VariablesWindow extends ListWindow implements VariablesObserver{


	public VariablesWindow(int w, int h) {
		super(w, h, "User Defined Variables");
	}
	

	


	@Override
	public void update(VariablesCollectionUpdate vcu) {
		List<StringProperty> nameList = vcu.getNameProperties();
		List<StringProperty> valueList = vcu.getDisplayProperties();
		for (int i=0; i<nameList.size(); i++){
			String name = nameList.get(i).getName();
			String value = valueList.get(i).getValue();
			VariableLabel vl = new VariableLabel(name, value);
			myList.getChildren().add(vl);	
		}
		
	}











	

}
