package view;

import javafx.scene.control.TextField;

public class InputDialogBox extends DialogBox {
	
	protected TextField myTextField;
	
	public InputDialogBox(String message) {
		super(message);
	}
	
	public String showInputDialog() {
		myStage.showAndWait();		
		return myTextField.getText();
		
	}
	
	@Override
	protected void createDialogBox() {
		super.createDialogBox();
		addInputField();
	}
	
	private void addInputField() {
		myTextField = new TextField();
		myPane.setCenter(myTextField);
	}

	
}
