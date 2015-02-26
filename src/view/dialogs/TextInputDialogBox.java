package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

public class TextInputDialogBox extends InputDialogBox {

	private TextField myTextField;
	private static final int PADDING = 10;

	public TextInputDialogBox(String prompt) {
		super(prompt);
	}
	
	@Override
	protected void updateUserInput() {
		userInput = myTextField.getText();
		myTextField.clear();
	}
	
	@Override
	protected void addInputField() {
		myTextField = new TextField();
		myPane.setCenter(myTextField);
		BorderPane.setMargin(myTextField, new Insets(PADDING));
		BorderPane.setAlignment(myTextField, Pos.CENTER);
	}
	
	public void setText(String value) {
		myTextField.setText(value);
	}

}
