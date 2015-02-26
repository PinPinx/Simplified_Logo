package view.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * DialogBox that includes a TextField to retrieve user input.
 * 
 * @author lien
 *
 */
public abstract class InputDialogBox extends MessageDialogBox {
	
	protected Object userInput;
	
	public InputDialogBox(String prompt) {
		
		super(prompt);
		addInputField();
	}
	
	public Object showInputDialog() {
		myStage.showAndWait();
		updateUserInput();
		return userInput;
	}
	
	protected abstract void addInputField();
	protected abstract void updateUserInput();
	
}
