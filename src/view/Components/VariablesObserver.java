package view.Components;

import java.util.List;



import javafx.beans.property.StringProperty;

public interface VariablesObserver{
	
	public void update(List<StringProperty> nameList, List<StringProperty> valueList);

}
