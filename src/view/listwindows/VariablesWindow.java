package view.listwindows;

import java.util.List;

import view.components.VariablesObserver;
import javafx.beans.property.StringProperty;
import model.VariablesCollectionUpdate;

public class VariablesWindow extends ListWindow implements VariablesObserver {
	
	private static final String UD_VARIABLES = "User Defined Variables";
	
	public VariablesWindow(int w, int h) {
		super(w, h, UD_VARIABLES);
	}

	@Override
	public void update(VariablesCollectionUpdate vcu) {
		myList.getChildren().clear();
		List<StringProperty> nameList = vcu.getNameProperties();
		List<StringProperty> valueList = vcu.getDisplayProperties();
		for (int i = 0; i < nameList.size(); i++) {
			VariableLabel vl = new VariableLabel(nameList.get(i),
					valueList.get(i));
			myList.getChildren().add(vl);
		}

	}

}
