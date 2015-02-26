package view.components;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

public class VariableLabel extends HBox {
	Label myVarLabel;
	StringProperty myName;
	Label myValueLabel;
	StringProperty myValue;
	TextField myTextField;

	public VariableLabel(StringProperty myName, StringProperty myValue) {
		myVarLabel = new Label(myName.getValue().substring(1) + " = ");
		myValueLabel = new Label(myValue.getValue());
		myValueLabel.setOnMouseEntered(e -> startEdit());
		this.myValue = myValue;
		this.getChildren().addAll(myVarLabel, myValueLabel);
	}

	public void startEdit() {
		myTextField = new TextField();
		myTextField.setOnMouseExited(e -> exitEdit());
		myTextField.setOnKeyPressed(e -> processEdit(e));
		this.getChildren().remove(myValueLabel);
		this.getChildren().add(myTextField);
	}

	public void exitEdit() {
		this.getChildren().remove(myTextField);
		this.getChildren().add(myValueLabel);
	}

	public void processEdit(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			// myValueLabel.setText(myTextField.getText());

			String value = myTextField.getText();
			myTextField.clear();

			myValue.setValue(value);
			myValueLabel.setText(myValue.getValue());

			exitEdit();

		}
	}

}
