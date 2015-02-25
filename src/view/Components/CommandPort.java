package view.Components;

import Exceptions.CommandNameNotFoundException;
import model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class CommandPort extends ScrollPane implements ViewComponent {
	private TextField myTextField;
	private Button myRunButton;

	public CommandPort(double width, double height) {
		HBox hb = new HBox(5);
		myRunButton = new Button("Run");
		myRunButton.setOnAction(e -> runCommand());
		myTextField = new TextField();
		myTextField.setPrefHeight(height);
		myTextField.setPrefWidth(width);
		myTextField.setText("Enter your command Here");
		myTextField.setOnKeyPressed(e -> runCommand(e));
		hb.getChildren().addAll(myTextField, myRunButton);
		this.setContent(hb);
	}

	private void runCommand() {
		// Send command to parser here.
		String command = myTextField.getText();
		try {
			Model.getInstance().parse(command);
		} catch (CommandNameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myTextField.clear();
	}

	private void runCommand(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			runCommand();
		}
	}

	@Override
	public void update(Object updateObject) {
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub

	}

}
