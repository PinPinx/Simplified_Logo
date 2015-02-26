package view.components;

import view.View;
import exceptions.BadArgumentException;
import exceptions.CommandNameNotFoundException;
import exceptions.SyntaxErrorWrongFormat;
import model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class CommandPort extends ScrollPane {

	private static final String COMMAND_PROMPT = "Enter your command here";
	private static final String RUN = "Run";

	private TextField myTextField;
	private Button myRunButton;

	public CommandPort(double width, double height) {
		HBox hb = new HBox(5);
		myRunButton = new Button(RUN);
		myRunButton.setOnAction(e -> runCommand());
		myTextField = new TextField();
		myTextField.setPrefHeight(height);
		myTextField.setPrefWidth(width);
		myTextField.setText(COMMAND_PROMPT);
		myTextField.setOnKeyPressed(e -> runCommand(e));
		myTextField.setOnMouseClicked(e -> clearField(myTextField));
		hb.getChildren().addAll(myTextField, myRunButton);
		this.setContent(hb);
	}

	private void runCommand() {
		// Send command to parser here.
		String command = myTextField.getText();
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException | SyntaxErrorWrongFormat | BadArgumentException e) {
			// TODO Auto-generated catch block
			View.getInstance().showDialog(e.getMessage());
		}
		myTextField.clear();
	}

	private void runCommand(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			runCommand();
		}
	}

	private void clearField(TextField tf) {
		if (tf.getText().equals(COMMAND_PROMPT)) {
			tf.clear();
		}
	}

}
