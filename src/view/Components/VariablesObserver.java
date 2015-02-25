package view.Components;

import java.util.List;




import model.VariablesCollectionUpdate;
import javafx.beans.property.StringProperty;

public interface VariablesObserver{
	
	public void update(VariablesCollectionUpdate vcu);

}
