package view.Components;


import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import view.*;

public class CommandPort extends ScrollPane implements ViewComponent{
	
	
	public CommandPort(double width, double height){
		HBox hb = new HBox();
		Button run = new Button("Run");
		TextField textField = new TextField ();
		textField.setPrefHeight(height);
		textField.setPrefWidth(width);
		textField.setText("Enter your command Here");
		hb.getChildren().addAll(textField, run);
		this.setContent(hb);
	}

	@Override
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UIEvent() {
		// TODO Auto-generated method stub
		
	}

}
