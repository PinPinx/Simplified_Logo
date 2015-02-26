package view.dialogs;

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
