package view.listwindows;

import java.util.List;

import view.components.Observer;
import javafx.beans.property.StringProperty;
import model.VariablesCollectionUpdate;

public class VariablesWindow extends ListWindow implements Observer {

	private static final String UD_VARIABLES = "User Defined Variables";

	public VariablesWindow(int w, int h) {
		super(w, h, UD_VARIABLES);
	}

	@Override
	public void update(Object o) {
		if(o instanceof VariablesCollectionUpdate){
			VariablesCollectionUpdate vcu = (VariablesCollectionUpdate) o;
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

}
