package view.toolbar;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class AddTurtleToolbarItem extends ToolbarItem {

	protected AddTurtleToolbarItem(String label, Toolbar container) {
		super(label, container);
	}

	@Override
	protected Node createNode() {
		Button turtle = new Button("New");
		turtle.setOnAction(e->{
			myToolbar.getTurtleWindow().addTurtle();
		});
		return turtle;
	}

}
