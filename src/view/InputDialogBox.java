package view;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * DialogBox that includes a TextField to retrieve user input.
 * 
 * @author lien
 *
 */
public class InputDialogBox extends DialogBox {
	
	private TextField myTextField;
	private static final int PADDING= 10;
	
	public InputDialogBox(String prompt) {
		super(prompt);
	}
	
	public String showInputDialog() {
		myStage.showAndWait();		
		return myTextField.getText();
	}
	
	@Override
	protected void createDialogBox() {
		addInputField();
		super.createDialogBox();
	}
	
	private void addInputField() {
		myTextField = new TextField();
		myPane.setCenter(myTextField);
		BorderPane.setMargin(myTextField, new Insets(PADDING));
	}
	
}
