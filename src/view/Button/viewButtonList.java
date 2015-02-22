package view.Button;

import view.Button.*;
import view.Components.ViewComponent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public abstract class viewButtonList extends ScrollPane implements ViewComponent {
	protected VBox content = new VBox();
	
	public void addButton(customButton b){
		content.getChildren().add(b);
		this.setContent(content);
	}
	
	public void setDimensions(){
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	}

}
