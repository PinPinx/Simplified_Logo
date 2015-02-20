package view.Components;

import view.ViewComponent;
import view.Button.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public abstract class viewButtonList extends ScrollPane implements ViewComponent {
	protected VBox content = new VBox();
	
	
	public void addButton(customButton b){
		content.getChildren().add(b);
		this.setContent(content);
	}

}
