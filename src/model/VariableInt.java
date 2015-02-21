package model;

public class VariableInt implements Variable {
	private String myName;
	private String myValue;
	
	public VariableInt(String name, Integer value){
		myName = name;
		myValue = value.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return myName;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return myValue;
	}
	
	

}
