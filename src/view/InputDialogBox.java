package view;

import javafx.scene.control.TextField;

/**
 * DialogBox that includes a TextField to retrieve user input.
 * 
 * @author lien
 *
 */
public class InputDialogBox extends DialogBox {
	
	protected TextField myTextField;
	
	public InputDialogBox(String prompt) {
		super(prompt);
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
