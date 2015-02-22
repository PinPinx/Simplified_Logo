package view.Components;


import model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import view.*;

public class CommandPort extends ScrollPane implements ViewComponent {
	private TextField myTextField;
	private Button myRunButton;
	
	
	public CommandPort(double width, double height){
		HBox hb = new HBox();
		myRunButton = new Button("Run");
		myRunButton.setOnAction(e->runCommand());
		myTextField = new TextField ();
		myTextField.setPrefHeight(height);
		myTextField.setPrefWidth(width);
		myTextField.setText("Enter your command Here");
		hb.getChildren().addAll(myTextField, myRunButton);
		this.setContent(hb);
	}

	private void runCommand() {
		//Send command to parser here. 
		String command = myTextField.getText();
		Model.getInstance().parse(command);
		myTextField.clear();
	}

	@Override
	public void update(Object updateObject) {
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub
		
		
	}

}
