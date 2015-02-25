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
		myList.getChildren().clear();
		List<StringProperty> nameList = vcu.getNameProperties();
		List<StringProperty> valueList = vcu.getDisplayProperties();
		for (int i=0; i<nameList.size(); i++){
			VariableLabel vl = new VariableLabel(nameList.get(i), valueList.get(i));
			myList.getChildren().add(vl);	
		}
		
	}











	

}
