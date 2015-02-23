package view.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class ToolbarItem extends HBox {
	
	protected TurtleWindow myTurtleWindow;
	protected Node myNode;
	
	protected ToolbarItem(String label, TurtleWindow tw) {
		myTurtleWindow = tw;
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(5));
		this.setBackground(new Background(new BackgroundFill(Color.GREY, null, null)));
		Label myLabel = new Label(label);
		this.getChildren().add(myLabel);
	}
	
	protected void setNode(Node node) {
		myNode = node;
		this.getChildren().add(node);
	}

}
