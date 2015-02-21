package model;

public class VariableString extends Variable {
	private String myValue;

	@Override
	public Object getValue() {
		return new String(this.myValue);
	}
}
