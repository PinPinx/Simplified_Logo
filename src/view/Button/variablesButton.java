package view.Button;


public class variablesButton extends customButton{

	public variablesButton(String name, String value) {
		super(name, value);
		String label = name+" = "+value;
		this.setText(label);
	}
	
}
