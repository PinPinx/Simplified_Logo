package model;

import java.util.List;

import javafx.beans.property.StringProperty;

public class VariablesCollectionUpdate {
	private List<StringProperty> myDisplayProperties;
	private List<StringProperty> myNameProperties;
	
	public VariablesCollectionUpdate(
			List<StringProperty> nameProps, List<StringProperty> displayProps){
		this.myDisplayProperties = displayProps;
		this.myNameProperties = nameProps;
	}
	
	public List<StringProperty> getDisplayProperties(){
		return myDisplayProperties;
	}
	public List<StringProperty> getNameProperties(){
		return myNameProperties;
	}
}
